package com.example.data.retrofit

import android.net.Uri
import android.util.Log
import com.example.domain.usecase.file.GetInputStreamUseCase
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.FileNotFoundException
import okio.source

class UriRequestBody(
    private val contentUri: String,
    private val getInputStreamUseCase: GetInputStreamUseCase,
    private val contentType: MediaType? = null,
    private val contentLength: Long
) : RequestBody() {
    override fun contentType(): MediaType? {
        return contentType
    }
    
    override fun contentLength(): Long {
        return contentLength
    }
    
    override fun writeTo(sink: BufferedSink) {
        try{
            getInputStreamUseCase(contentUri).getOrThrow()
                .use { inputStream ->
                    sink.writeAll(inputStream.source())
                }
        }catch (e: FileNotFoundException) {
            Log.e("UriRequestBody", "${e.message}")
        }
    }
}
