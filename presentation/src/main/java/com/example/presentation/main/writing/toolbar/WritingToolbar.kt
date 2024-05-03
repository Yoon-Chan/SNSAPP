package com.example.presentation.main.writing.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.SnsProjectTheme
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState

@Composable
fun WritingToolbar(
    modifier: Modifier = Modifier,
    richTextState: RichTextState
//     currentType: WritingToolbarType,
//     onTypeClick: (WritingToolbarType) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            modifier = Modifier
                .padding(8.dp),
            onClick = {
                richTextState.toggleSpanStyle(
                    spanStyle = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            shape = RectangleShape,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = WritingToolbarType.BOLD.resId),
                contentDescription = WritingToolbarType.BOLD.name,
                tint = if (richTextState.currentSpanStyle.fontWeight==FontWeight.Bold) Color.Black else Color.Gray
            )
        }
        
        FilledIconButton(
            modifier = Modifier
                .padding(8.dp),
            onClick = {
                richTextState.toggleSpanStyle(
                    spanStyle = SpanStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
            },
            shape = RectangleShape,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = WritingToolbarType.ITALIC.resId),
                contentDescription = WritingToolbarType.ITALIC.name,
                tint = if (richTextState.currentSpanStyle.fontStyle==FontStyle.Italic) Color.Black else Color.Gray
            )
        }
        
        FilledIconButton(
            modifier = Modifier
                .padding(8.dp),
            onClick = {
                richTextState.toggleSpanStyle(
                    spanStyle = SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            },
            shape = RectangleShape,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = WritingToolbarType.UNDERLINE.resId),
                contentDescription = WritingToolbarType.UNDERLINE.name,
                tint = if (richTextState.currentSpanStyle.textDecoration?.contains(TextDecoration.Underline)==true) Color.Black else Color.Gray
            )
        }
        
        FilledIconButton(
            modifier = Modifier
                .padding(8.dp),
            onClick = {
                richTextState.toggleSpanStyle(
                    spanStyle = SpanStyle(
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            },
            shape = RectangleShape,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = WritingToolbarType.STRIKETHROUGH.resId),
                contentDescription = WritingToolbarType.STRIKETHROUGH.name,
                tint = if (richTextState.currentSpanStyle.textDecoration?.contains(TextDecoration.LineThrough)==true) Color.Black else Color.Gray
            )
        }
    }
    
}


@Preview
@Composable
private fun WritingToolbarPreview() {
    SnsProjectTheme {
        var richTextState = rememberRichTextState()
        WritingToolbar(
            Modifier,
            richTextState = richTextState
        )
    }
}
