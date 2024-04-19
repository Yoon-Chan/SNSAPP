package com.example.data.usecase

import com.example.data.model.SignUpParam
import com.example.data.retrofit.UserService
import com.example.domain.usecase.login.SignUpUseCase
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

class SignUpUseCaseImpl @Inject constructor(
    private val userService: UserService
) : SignUpUseCase {
    override suspend fun invoke(id: String, username: String, password: String): Result<Boolean> = runCatching{
        val requestBody = SignUpParam(id, username, password, "", "").toRequestBody()
        userService.signUp(requestBody = requestBody).result == "SUCCESS"
    }
}
