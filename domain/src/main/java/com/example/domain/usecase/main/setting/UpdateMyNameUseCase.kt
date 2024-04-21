package com.example.domain.usecase.main.setting

interface UpdateMyNameUseCase {
    suspend operator fun invoke(userName : String) : Result<Unit>
}
