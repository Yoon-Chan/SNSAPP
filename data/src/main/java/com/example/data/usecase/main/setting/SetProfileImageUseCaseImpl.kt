package com.example.data.usecase.main.setting

import com.example.data.BuildConfig
import com.example.domain.model.Image
import com.example.domain.usecase.file.GetImageUseCase
import com.example.domain.usecase.file.UploadImageUseCase
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import com.example.domain.usecase.main.setting.SetMyUserUseCase
import com.example.domain.usecase.main.setting.SetProfileImageUseCase
import javax.inject.Inject

class SetProfileImageUseCaseImpl @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val setMyUserUseCase: SetMyUserUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,
): SetProfileImageUseCase {
    override suspend fun invoke(contentUri: String) : Result<Unit> = runCatching{
        //0. 현재 user 정보 가져오기
        val user = getMyUserUseCase().getOrThrow()
        
        // 1. 이미지 가져오기
        val image = getImageUseCase(contentUri) ?: throw NullPointerException("이미지를 찾을 수 없습니다.")
        
        //2. 이미지 서버에 업로드하기
        val imagePath = uploadImageUseCase(image).getOrThrow()
        
        // 4. 내 정보 업데이트
        setMyUserUseCase(
            userName = user.username,
            profileImageUrl = imagePath
        ).getOrThrow()
        
    }
}
