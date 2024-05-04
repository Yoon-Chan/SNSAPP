package com.example.presentation.main.board.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.domain.model.Comment
import com.example.presentation.component.LoginTextField
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun CommentDialog(
    userId: Long,
    comments: List<Comment>,
    onDismissRequest: () -> Unit,
    onCloseClick: () -> Unit,
    onCommentSend: (String) -> Unit,
    onDeleteComment: (Comment) -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties =
            DialogProperties(
                usePlatformDefaultWidth = false,
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(0.5f),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "댓글",
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = onCloseClick) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "닫기",
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                    ) {
                        items(count = comments.size, key = { index ->
                            comments[index].id
                        }) { index ->
                            val comment = comments[index]
                            CommentCard(
                                isMine = userId == comment.createUserId,
                                modifier = Modifier,
                                profileImageUrl = comment.profileImageUrl,
                                username = comment.username,
                                text = comment.text,
                                onDeleteComment = {
                                    onDeleteComment(comment)
                                },
                            )
                        }
                    }
                    HorizontalDivider()
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LoginTextField(
                            modifier = Modifier.weight(1f),
                            value = text,
                            onValueString = { text = it },
                        )

                        IconButton(onClick = {
                            onCommentSend(text)
                            text = ""
                        }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "전송")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CommentDialogPreview() {
    SnsProjectTheme {
        CommentDialog(
            userId = 0,
            onCommentSend = {},
            onCloseClick = {},
            onDismissRequest = {},
            comments = listOf(),
            onDeleteComment = {},
        )
    }
}
