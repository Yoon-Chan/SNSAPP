package com.example.data.retrofit

import com.example.data.UserDataStore
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AddInterceptor @Inject constructor(
    private val userDataStore: UserDataStore
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        
        val token: String? = runBlocking { userDataStore.getToken() }
        return chain.proceed(
            chain.request()
                .newBuilder()
                .run {
                    token?.let {
                        this.addHeader("Token", token)
                    } ?: this
                }
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .build()
        )
    }
}
