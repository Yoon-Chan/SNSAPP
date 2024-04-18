package com.example.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.component.LoginTextField
import com.example.presentation.component.SubmitButton
import com.example.presentation.ui.theme.SnsProjectTheme

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
    onSignUpClick: () -> Unit,
) {
    Surface {
        Column(
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
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally),
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
