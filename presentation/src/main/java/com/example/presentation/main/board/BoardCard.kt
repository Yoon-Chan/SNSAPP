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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Image
import com.example.presentation.component.ImagePager
import com.example.presentation.ui.theme.SnsProjectTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoardCard(
    profileImageUrl: String? = null,
    username: String,
    images: List<String>,
    text: String,
    onOptionClick: () -> Unit,
    onReplyClick: () -> Unit
) {
    Surface {
        val pagerState = rememberPagerState(
            pageCount = { images.size }
        )
        
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(16.dp))
        ) {
            //Header
            BoardHeader(
                modifier = Modifier.fillMaxWidth(),
                profileImageUrl = profileImageUrl,
                username = username,
                onOptionClick = onOptionClick)
            //ImagePager
            if (images.isNotEmpty()) {
                ImagePager(
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                    images = images,
                    pagerState = pagerState
                )
            }
            var maxLines by remember {
                mutableIntStateOf(1)
            }
            var showMore by remember {
                mutableStateOf(false)
            }
            //content
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = text,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    showMore = textLayoutResult.didOverflowHeight
                }
            )
            
            if (showMore) {
                TextButton(onClick = { maxLines = Int.MAX_VALUE }) {
                    Text(
                        text = "더보기",
                        style = MaterialTheme.typography.labelLarge)
                }
            }
            
            
            //comment
            TextButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.End),
                onClick = onReplyClick) {
                Text(text = "댓글")
            }
        }
        
    }
}

@Preview
@Composable
private fun BoardCardPreview() {
    SnsProjectTheme {
        BoardCard(
            profileImageUrl = null,
            username = "이름",
            images = listOf(),
            text = "내용\n내용\n내용\n내용\n내용\n내용\n내용\n내용\n12312312내용\n내용\n12313",
            onOptionClick = {},
            onReplyClick = {}
        )
    }
}
