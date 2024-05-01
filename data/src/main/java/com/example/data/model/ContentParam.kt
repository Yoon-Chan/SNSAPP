package com.example.data.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentParam(
    val text: String,
    val images:List<String>
):Parcelable {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}
