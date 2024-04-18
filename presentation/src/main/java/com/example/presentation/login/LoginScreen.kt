package com.example.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.component.LoginTextField
import com.example.presentation.component.SubmitButton
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun LoginScreen(
    viewmodel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        id = "",
        password = "",
        onIdChange = {},
        passwordChange = {},
        onNavigationToSignUpScreen = { viewmodel.onLoginClick() }
    )
}

@Composable
fun LoginScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onNavigationToSignUpScreen: () -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Connected", style = MaterialTheme.typography.displaySmall)
                Text(text = "Your favorite social network", style = MaterialTheme.typography.labelSmall)
            }
            
            Column(
                modifier =
                Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(36.dp),
                    text = "Log in",
                    style = MaterialTheme.typography.headlineMedium,
                )
                
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Id",
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(
                    modifier =
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueString = onIdChange,
                )
                
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "password",
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(
                    modifier =
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueString = passwordChange,
                )
                
                SubmitButton(
                    modifier =
                    Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    text = "로그인",
                    onClick = {},
                )
                
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
                    .clickable(onClick = onNavigationToSignUpScreen)) {
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
