package com.example.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.presentation.component.*
import com.example.presentation.ui.theme.*

@Composable
fun WelcomeScreen() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Connected", style = MaterialTheme.typography.displayLarge)
                Text(text = "Your favorite social network", style = MaterialTheme.typography.labelLarge)
                
            }
            
            SubmitButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomCenter),
                text = "로그인",
                onClick = {}
            )
        }
    }
}


@Preview
@Composable
private fun WelcomeScreenPreview() {
    SnsProjectTheme {
        WelcomeScreen()
    }
}
