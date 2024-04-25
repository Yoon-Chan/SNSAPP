package com.example.presentation.main.setting

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.component.ProfileImage
import com.example.presentation.login.LoginActivity
import com.example.presentation.ui.theme.SnsProjectTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SettingScreen(viewModel: SettingViewModel = hiltViewModel()) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    var usernameDialogVisible by remember {
        mutableStateOf(false)
    }
    viewModel.collectSideEffect { sideEffect: SettingSideEffect ->
        when (sideEffect) {
            is SettingSideEffect.Toast -> {
                Log.e("SettingScreen", sideEffect.message)
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            is SettingSideEffect.NavigateToLoginActivity -> {
                context.startActivity(
                    Intent(context, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    },
                )
            }
        }
    }

    val visualMediaPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
            viewModel.onImageChange(it)
        }

    SettingScreen(
        username = state.username,
        profileImageUrl = state.profileImageUrl,
        onNameChangeClick = { usernameDialogVisible = true },
        onLogoutClick = viewModel::onLogoutClick,
        onImageChangeClick = {
            visualMediaPickerLauncher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly),
            )
        },
    )

    UsernameDialog(
        visible = usernameDialogVisible,
        initialUsername = state.username,
        onDismissRequest = { usernameDialogVisible = false },
        onUserNameChange = viewModel::onUsernameChange,
    )
}

@Composable
fun SettingScreen(
    username: String,
    profileImageUrl: String?,
    onImageChangeClick: () -> Unit,
    onNameChangeClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
            ProfileImage(
                modifier = Modifier.size(150.dp),
                profileImageUrl = profileImageUrl,
            )

            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onImageChangeClick,
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(30.dp)
                            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                            .background(color = Color.White, shape = CircleShape),
                ) {
                    Icon(
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .size(20.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
        Text(
            modifier =
                Modifier
                    .padding(top = 8.dp)
                    .clickable { onNameChangeClick() },
            text = username,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onLogoutClick,
        ) {
            Text(text = "로그아웃")
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SnsProjectTheme {
        SettingScreen(
            username = "YYYY",
            profileImageUrl = null,
            onImageChangeClick = {},
            onLogoutClick = {},
            onNameChangeClick = {},
        )
    }
}
