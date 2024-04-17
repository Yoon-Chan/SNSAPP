package com.example.presentation.component

import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*


@Composable
fun LoginTextField(
    modifier: Modifier,
    value: String,
    onValueString: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueString,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}
