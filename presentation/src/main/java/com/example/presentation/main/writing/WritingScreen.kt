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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.component.ImagePager
import com.example.presentation.component.LoginTextField
import com.example.presentation.ui.theme.SnsProjectTheme
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WritingScreen(
    viewModel: WritingViewModel,
    onBackClick: () -> Unit,
    onPostClick: () -> Unit,
) {
    val state = viewModel.collectAsState().value
    val pageState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.images.size },
    )
    
    WritingScreen(
        images = state.images.map { it.uri },
        text = state.text,
        onTextChange = viewModel::onTextChange,
        pagerState = pageState,
        onBackClick = onBackClick,
        onPostClick = viewModel::onPostClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WritingScreen(
    images: List<String>,
    pagerState: PagerState,
    text: String,
    onTextChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onPostClick: () -> Unit
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
                    ImagePager(modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f), images = images, pagerState = pagerState)
                    
                    HorizontalDivider()
                    
                    LoginTextField(modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f), value = text, onValueString = onTextChange)
                }
            },
            
            )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun WritingScreenPreview() {
    SnsProjectTheme {
        WritingScreen(
            images = listOf(),
            text = "",
            onTextChange = {},
            pagerState = rememberPagerState(pageCount = { 10 }),
            onBackClick = {},
            onPostClick = {}
        )
    }
}
