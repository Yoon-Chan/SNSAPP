package com.example.presentation.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.presentation.component.*
import com.example.presentation.ui.theme.*

@Composable
fun SignUpScreen(
    id: String,
    username: String,
    password1: String,
    password2: String,
    onIdChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPassword1Change: (String) -> Unit,
    onPassword2Change: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    
    Surface {
        Column(
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
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Create an account",
                    modifier = Modifier.padding(top = 36.dp),
                    style = MaterialTheme.typography.headlineMedium,
                )
                
                Text(
                    text = "Id",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(), value = id, onValueString = onIdChange)
                
                Text(
                    text = "username",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(), value = username, onValueString = onUsernameChange)
                
                Text(
                    text = "password",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(), value = password1, onValueString = onPassword1Change)
                
                Text(
                    text = "Repeat password",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
                LoginTextField(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(), value = password2, onValueString = onPassword2Change)
                
                SubmitButton(modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(), text = "Sign Up", onClick = onSignUpClick)
            }
        }
    }
    
}


@Preview
@Composable
private fun SignUpScreenPreview() {
    SnsProjectTheme {
        SignUpScreen("", "", "", "", {}, {}, {}, {}, {})
    }
}
