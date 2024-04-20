package com.example.data.retrofit

import com.example.data.model.CommonResponse
import com.example.data.model.UserDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST("users/login")
    @Headers("Content-Type:application/json; charset=UTF-8")
    suspend fun login(
        @Body requestBody: RequestBody
    ) : CommonResponse<String>
    
    @POST("users/sign-up")
    @Headers("Content-Type:application/json; charset=UTF-8")
    suspend fun signUp(
        @Body requestBody: RequestBody
    ): CommonResponse<Int>
    
    @GET("users/my-page")
    @Headers("Content-Type:application/json; charset=UTF-8")
    suspend fun myPage(): CommonResponse<UserDto>
}
