package com.example.domain.model

data class Board(
    val id: Long,
    val title: String,
    val content: String,
    val images: List<String> = listOf(),
    val userId: Long,
    val username: String,
    val profileImageUrl: String,
    val comments: List<Comment>
)
