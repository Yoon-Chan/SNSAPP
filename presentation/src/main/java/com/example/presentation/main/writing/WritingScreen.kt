package com.example.presentation.main.writing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.component.ImagePager
import com.example.presentation.component.LoginTextField
import com.example.presentation.main.writing.toolbar.WritingToolbar
import com.example.presentation.main.writing.toolbar.WritingToolbarType
import com.example.presentation.ui.theme.SnsProjectTheme
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.BasicRichTextEditor
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WritingScreen(
    viewModel: WritingViewModel,
    onBackClick: () -> Unit,
) {
    val state = viewModel.collectAsState().value
    val pageState =
        rememberPagerState(
            initialPage = 0,
            pageCount = { state.selectedImages.size },
        )
    
    WritingScreen(
        richTextState = state.richTextState,
        images = state.selectedImages.map { it.uri },
        pagerState = pageState,
        onBackClick = onBackClick,
        onPostClick = viewModel::onPostClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WritingScreen(
    richTextState: RichTextState,
    images: List<String>,
    pagerState: PagerState,
    onBackClick: () -> Unit,
    onPostClick: () -> Unit,
) {
    Surface {
        Scaffold(
            topBar =
            {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "새 게시물", style = MaterialTheme.typography.headlineSmall)
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                        }
                    },
                    actions = {
                        TextButton(onClick = onPostClick) {
                            Text(text = "다음", style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                        }
                    },
                )
            },
            content = { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    ImagePager(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        images = images,
                        pagerState = pagerState,
                    )
                    
                    HorizontalDivider()
                    
                    BasicRichTextEditor(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f),
                        state = richTextState,
                        cursorBrush = SolidColor(Color.Black),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        decorationBox = { innerTextField ->
                            if (richTextState.annotatedString.isEmpty()) {
                                Text(
                                    modifier = Modifier.alpha(0.5f),
                                    text = "문구를 입력해 주세요."
                                )
                            }
                            innerTextField()
                        },
                    )
                    
                }
            },
            bottomBar = {
                WritingToolbar(
                    modifier = Modifier.fillMaxWidth(),
                    richTextState = richTextState
                )
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun WritingScreenPreview() {
    SnsProjectTheme {
        WritingScreen(
            richTextState = rememberRichTextState(),
            images = listOf(),
            pagerState = rememberPagerState(pageCount = { 10 }),
            onBackClick = {},
            onPostClick = {},
        )
    }
}
