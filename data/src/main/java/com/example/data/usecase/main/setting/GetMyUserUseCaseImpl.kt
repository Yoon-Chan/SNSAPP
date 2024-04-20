package com.example.data.usecase.main.setting

import android.util.Log
import com.example.data.retrofit.UserService
import com.example.domain.model.User
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import javax.inject.Inject

class GetMyUserUseCaseImpl @Inject constructor(
    private val userService: UserService
) : GetMyUserUseCase {
    override suspend fun invoke(): Result<User> = runCatching {
        val userDto = userService.myPage().data
        Log.e("GetMyUserUseCaseImpl", "userDto: $userDto")
        userDto.toUser()
    }
}
