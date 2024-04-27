package com.example.presentation.main

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.presentation.main.writing.WritingActivity
import com.example.presentation.ui.theme.SnsProjectTheme

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

@Composable
fun MainBottomBar(navController: NavController) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    val currentRoute =
        navBackStackEntry?.destination?.route?.let { currentRoute ->
            MainRoute.entries.find { route -> currentRoute == route.route }
        } ?: MainRoute.BOARD

    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = { isShowDialog = !isShowDialog },
            title = {
                Text(text = "현재 권한요청이 거부되었습니다. 권한요청을 변경하시겠습니까?")
            },
            confirmButton = {
                Text(
                    text = "설정하러 가기",
                    modifier =
                        Modifier.clickable {
                            context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${context.packageName}")))
                            isShowDialog = !isShowDialog
                        },
                )
            },
            dismissButton = {
                Text(text = "다음에", modifier = Modifier.clickable { isShowDialog = !isShowDialog })
            },
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
        ) { grant ->

            val areGranted = grant.values.reduce { acc, next -> acc && next }

            if (areGranted) {
                context.startActivity(
                    Intent(context, WritingActivity::class.java),
                )
            } else {
                Toast.makeText(context, "권한 요청을 등록해야 게시글을 작성할 수 있습니다.", Toast.LENGTH_SHORT).show()
                isShowDialog = !isShowDialog
            }
        }

    MainBottomBar(currentRoute = currentRoute, onItemClick = { newRoute ->
        if (newRoute == MainRoute.WRITING) {
            val array =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO)
                } else {
                    arrayOf(READ_EXTERNAL_STORAGE)
                }

            if (array.all { ContextCompat.checkSelfPermission(context, it) == PermissionChecker.PERMISSION_GRANTED }) {
                context.startActivity(
                    Intent(context, WritingActivity::class.java),
                )
            } else {
                permissionLauncher.launch(array)
            }
        } else {
            navController.navigate(route = newRoute.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                    this.launchSingleTop = true
                    this.restoreState = true
                }
            }
        }
    })
}

@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit,
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
    ) {
        HorizontalDivider()
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            MainRoute.entries.forEach { route ->
                IconButton(onClick = { onItemClick(route) }) {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription,
                        tint =
                            if (currentRoute == route) {
                                Color.Black
                            } else {
                                Color.White
                            },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    SnsProjectTheme {
        var currentRoute by remember {
            mutableStateOf(MainRoute.BOARD)
        }

        MainBottomBar(
            currentRoute = currentRoute,
            onItemClick = { newRoute -> currentRoute = newRoute },
        )
    }
}
