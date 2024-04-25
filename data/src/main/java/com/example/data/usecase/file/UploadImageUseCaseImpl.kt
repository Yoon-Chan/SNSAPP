package com.example.data.usecase.file

import com.example.data.retrofit.FileService
import com.example.data.retrofit.UriRequestBody
import com.example.domain.model.Image
import com.example.domain.usecase.file.GetInputStreamUseCase
import com.example.domain.usecase.file.UploadImageUseCase
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody

class UploadImageUseCaseImpl @Inject constructor(
    private val fileService: FileService,
    private val getInputStreamUseCase: GetInputStreamUseCase
): UploadImageUseCase {
    override suspend fun invoke(image: Image): Result<String> = runCatching {
        val fileNamePart = MultipartBody.Part.createFormData(
            "fileName",
            image.name
        )
        
        val requestBody = UriRequestBody(
            image.uri,
            getInputStreamUseCase,
            image.mimeType.toMediaType(),
            image.size
        )
        
        val filePart = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestBody
        )
        fileService.uploadFile(
            fileNamePart,
            filePart
        ).data.filePath
    }
}
