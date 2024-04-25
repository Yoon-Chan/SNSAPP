package com.example.data.usecase.main.setting

import com.example.data.model.UpdateMyInfoParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import com.example.domain.usecase.main.setting.SetMyUserUseCase
import javax.inject.Inject

class SetMyUserUseCaseImpl @Inject constructor(
    private val service: UserService,
    private val getMyUserUseCase: GetMyUserUseCase
) : SetMyUserUseCase {
    override suspend fun invoke(
        userName: String?,
        profileImageUrl: String?
        ): Result<Unit> = runCatching {
            val user = getMyUserUseCase().getOrThrow()
            val requestBody = UpdateMyInfoParam(
                userName = userName ?: user.username,
                profileFilePath = profileImageUrl ?: user.profileImageUrl.orEmpty(),
                extraUserInfo = "",
                
            ).toRequestBody()
            service.patchMyPage(requestBody)
    }
}
