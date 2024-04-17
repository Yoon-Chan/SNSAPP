package com.example.presentation.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.presentation.component.*
import com.example.presentation.ui.theme.*

@Composable
fun LoginScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onNavigationToSignUpScreen: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Connected", style = MaterialTheme.typography.displaySmall)
                Text(text = "Your favorite social network", style = MaterialTheme.typography.labelSmall)
                
            }
            
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(36.dp),
                    text = "Log in",
                    style = MaterialTheme.typography.headlineMedium
                )
                
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Id",
                    style = MaterialTheme.typography.labelLarge
                )
                LoginTextField(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), value = id, onValueString = onIdChange)
                
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "password",
                    style = MaterialTheme.typography.labelLarge
                )
                LoginTextField(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), value = password, onValueString = passwordChange)
                
                SubmitButton(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    text = "로그인",
                    onClick = {}
                )
                
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 24.dp).clickable(onClick = onNavigationToSignUpScreen)) {
                    Text(text = "Don't hava and account?")
                    Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    SnsProjectTheme {
        LoginScreen("id", "password", {}, {}, onNavigationToSignUpScreen = {})
    }
}
