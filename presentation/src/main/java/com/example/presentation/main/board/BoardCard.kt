package com.example.presentation.main.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Comment
import com.example.presentation.component.ImagePager
import com.example.presentation.main.board.comment.CommentDialog
import com.example.presentation.ui.theme.SnsProjectTheme
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.BasicRichText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoardCard(
    isMine: Boolean,
    boardId: Long,
    profileImageUrl: String? = null,
    username: String,
    images: List<String>,
    richTextState: RichTextState,
    commentList: List<Comment>,
    onOptionClick: () -> Unit,
    onDeleteComment: (Long, Comment) -> Unit,
    onCommentSend: (Long, String) -> Unit,
) {
    var commentDialogVisible by remember {
        mutableStateOf(false)
    }

    Surface {
        val pagerState =
            rememberPagerState(
                pageCount = { images.size },
            )

        Column(
            modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(16.dp)),
        ) {
            // Header
            BoardHeader(
                isMine = isMine,
                modifier = Modifier.fillMaxWidth(),
                profileImageUrl = profileImageUrl,
                username = username,
                onOptionClick = onOptionClick,
            )
            // ImagePager
            if (images.isNotEmpty()) {
                ImagePager(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    images = images,
                    pagerState = pagerState,
                )
            }
            var maxLines by remember {
                mutableIntStateOf(1)
            }
            var showMore by remember {
                mutableStateOf(false)
            }
            // content
            BasicRichText(
                modifier =
                Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    showMore = textLayoutResult.didOverflowHeight
                },
                state = richTextState,
                style = TextStyle.Default.copy(
                    color = Color.Black
                )
            )
            
            if (showMore) {
                TextButton(onClick = { maxLines = Int.MAX_VALUE }) {
                    Text(
                        text = "더보기",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }

            // comment
            TextButton(
                modifier =
                Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.End),
                onClick = { commentDialogVisible = true },
            ) {
                Text(text = "댓글")
            }

            if (commentDialogVisible) {
                CommentDialog(
                    isMine = isMine,
                    onDismissRequest = { commentDialogVisible = false },
                    comments = commentList,
                    onCloseClick = { commentDialogVisible = false },
                    onCommentSend = { text ->
                        onCommentSend(boardId, text)
                    },
                    onDeleteComment = { comment ->
                        onDeleteComment(boardId, comment)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun BoardCardPreview() {
    SnsProjectTheme {
        val richTextState = rememberRichTextState()
        BoardCard(
            isMine = true,
            boardId = 1,
            profileImageUrl = null,
            username = "이름",
            images = listOf(),
            richTextState = richTextState,
            onOptionClick = {},
            commentList = listOf(),
            onDeleteComment = { _, _ -> },
            onCommentSend = { _, _ -> },
        )
    }
}
