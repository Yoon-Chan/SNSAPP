package com.example.presentation.main.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.presentation.model.BoardCardModel
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun BoardOptionDialog(
    boardCardModel: BoardCardModel?,
    onDismissRequest: () -> Unit,
    onBoardDelete: (BoardCardModel) -> Unit,
) {
    if (boardCardModel != null) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    onClick = {
                        onBoardDelete(boardCardModel)
                        onDismissRequest()
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            contentColor = MaterialTheme.colorScheme.secondary,
                        ),
                ) {
                    Text(text = "삭제")
                }
            }
        }
    }
}

@Preview
@Composable
private fun BoardOptionDialogPreview() {
    SnsProjectTheme {
        var visible by remember {
            mutableStateOf(true)
        }
        BoardOptionDialog(
            boardCardModel = null,
            onDismissRequest = { visible = false },
            onBoardDelete = {},
        )
    }
}
