package com.example.data.usecase

import com.example.data.model.LoginParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.login.LoginUseCase
import javax.inject.Inject
import okhttp3.RequestBody

class LoginUseCaseImpl @Inject constructor(
    private val userService: UserService
): LoginUseCase {
    override suspend fun invoke(id: String, password: String): Result<String> = runCatching {
        val requestBody = LoginParam(id, password).toRequestBody()
        userService.login(requestBody).data
    }
}
