package com.example.data.usecase.main.setting

import com.example.data.model.UpdateMyInfoParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import com.example.domain.usecase.main.setting.UpdateMyNameUseCase
import javax.inject.Inject

class UpdateMyNameUseCaseImpl @Inject constructor(
    private val service: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
) : UpdateMyNameUseCase {
    override suspend fun invoke(userName: String): Result<Unit> = runCatching {
            val user = getMyUserUseCase().getOrThrow()
            val requestBody = UpdateMyInfoParam(
                userName = userName,
                extraUserInfo = user.profileImageUrl.orEmpty(),
                profileFilePath = "",
                
            ).toRequestBody()
            service.patchMyPage(requestBody)
    }
}
