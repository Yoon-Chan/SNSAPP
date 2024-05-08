package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.model.BoardDTO
import com.example.data.model.CommentDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun fromCommentListDto(value: List<CommentDTO>): String? = Gson().toJson(value)
    
    @TypeConverter
    fun toCommentListDto(json: String?): List<CommentDTO>? = Gson().fromJson(json, object : TypeToken<List<CommentDTO>>() {}.type)
    
    @TypeConverter
    fun fromCommentDto(value: CommentDTO): String? = Gson().toJson(value)
    
    @TypeConverter
    fun toCommentDto(json: String?): CommentDTO? = Gson().fromJson(json, CommentDTO::class.java)
    
    @TypeConverter
    fun fromBoardDto(value: BoardDao): String? = Gson().toJson(value)
    
    @TypeConverter
    fun toBoardDto(json: String?): BoardDTO? = Gson().fromJson(json, BoardDTO::class.java)
}
