package com.example.data.di

import com.example.data.usecase.ClearTokenUseCaseImpl
import com.example.data.usecase.GetTokenUseCaseImpl
import com.example.data.usecase.LoginUseCaseImpl
import com.example.data.usecase.SetTokenUseCaseImpl
import com.example.data.usecase.SignUpUseCaseImpl
import com.example.data.usecase.main.setting.GetMyUserUseCaseImpl
import com.example.domain.usecase.login.ClearTokenUseCase
import com.example.domain.usecase.login.GetTokenUseCase
import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.login.SetTokenUseCase
import com.example.domain.usecase.login.SignUpUseCase
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    
    @Binds
    abstract fun bindLoginUseCase(loginUseCase: LoginUseCaseImpl) : LoginUseCase
    
    @Binds
    abstract fun bindSignUpUseCase(signUpUseCase: SignUpUseCaseImpl) : SignUpUseCase
    
    @Binds
    abstract fun bindSetTokenUseCase(setTokenUseCaseImpl: SetTokenUseCaseImpl) : SetTokenUseCase
    
    @Binds
    abstract fun bindGetTokenUseCase(getTokenUseCaseImpl: GetTokenUseCaseImpl) : GetTokenUseCase
    
    @Binds
    abstract fun bindClearUseCase(clearTokenUseCaseImpl: ClearTokenUseCaseImpl) : ClearTokenUseCase
    
    @Binds
    abstract fun bindGetMyUserUseCase(getMyUserUseCaseImpl: GetMyUserUseCaseImpl) : GetMyUserUseCase
}
