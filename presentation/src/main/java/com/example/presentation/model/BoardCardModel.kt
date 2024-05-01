package com.example.presentation.model

data class BoardCardModel(
    val boardId: Long,
    val images: List<String>,
    val username: String,
    val text: String
)
