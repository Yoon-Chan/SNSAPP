package com.example.presentation.main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.presentation.main.writing.WritingActivity
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun MainBottomBar(navController: NavController) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route?.let { currentRoute ->
            MainRoute.entries.find { route -> currentRoute == route.route }
        } ?: MainRoute.BOARD

    MainBottomBar(currentRoute = currentRoute, onItemClick = { newRoute ->
        if (newRoute == MainRoute.WRITING) {
            context.startActivity(
                Intent(context, WritingActivity::class.java),
            )
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
