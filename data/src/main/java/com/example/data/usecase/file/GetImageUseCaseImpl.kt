package com.example.data.usecase.file

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.domain.model.Image
import com.example.domain.usecase.file.GetImageUseCase
import javax.inject.Inject

class GetImageUseCaseImpl @Inject constructor(
    private val context: Context
) : GetImageUseCase {
    
    override fun invoke(contentUri: String): Image? {
        val uri = Uri.parse(contentUri)
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
        )
        val cursor = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )
        
        return cursor?.use {
            it.moveToNext()
            val idIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val mimeIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            
            val name = it.getString(nameIndex)
            val size = it.getLong(sizeIndex)
            val mimeType = it.getString(mimeIndex)
            val image = Image(
                uri = contentUri,
                name = name,
                size = size,
                mimeType = mimeType
            )
            
            Log.e("GetImageUseCaseImpl curosr", "${image}")
            
            image
        }
    }
}
