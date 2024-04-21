package com.example.presentation.main.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun UsernameDialog(
    visible: Boolean,
    initialUsername: String,
    onUserNameChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    
    if (visible) {
        var username by remember {
            mutableStateOf(initialUsername)
        }
        Dialog(onDismissRequest = onDismissRequest) {
            Surface {
                Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = username,
                        onValueChange = { username = it },
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        
                    )
                    
                    Row {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onUserNameChange(username)
                                onDismissRequest()
                            }) {
                            Text(text = "변경")
                        }
                        
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = onDismissRequest) {
                            Text(text = "취소")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UsernameDialogPreview() {
    SnsProjectTheme {
        UsernameDialog(visible = true, initialUsername = "chan", onUserNameChange = {}, onDismissRequest = {})
    }
}
