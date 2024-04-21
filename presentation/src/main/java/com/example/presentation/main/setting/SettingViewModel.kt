package com.example.presentation.main.setting

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.login.ClearTokenUseCase
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import com.example.domain.usecase.main.setting.UpdateMyNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class SettingViewModel
@Inject
constructor(
    private val clearTokenUseCase: ClearTokenUseCase,
    private val getMyUserUseCase: GetMyUserUseCase,
    private val updateMyNameUseCase: UpdateMyNameUseCase
) : ViewModel(), ContainerHost<SettingState, SettingSideEffect> {
    override val container: Container<SettingState, SettingSideEffect> =
        container(
            initialState = SettingState(),
            buildSettings = {
                this.exceptionHandler =
                    CoroutineExceptionHandler { _, throwable ->
                        intent { postSideEffect(SettingSideEffect.Toast(throwable.message ?: "")) }
                    }
            },
        )
    
    init {
        load()
    }
    
    private fun load() =
        intent {
            val user = getMyUserUseCase().getOrThrow()
            Log.e("SettingViewModel", "user : $user")
            reduce {
                state.copy(
                    profileImageUrl = user.profileImageUrl,
                    username = user.username,
                )
            }
        }
    
    fun onLogoutClick() =
        intent {
            clearTokenUseCase().getOrThrow()
            postSideEffect(SettingSideEffect.NavigateToLoginActivity)
        }
    
    fun onUsernameChange(username: String) = intent {
        updateMyNameUseCase(username).getOrThrow()
        load()
    }
}

@Immutable
data class SettingState(
    val profileImageUrl: String? = null,
    val username: String = "",
)

sealed interface SettingSideEffect {
    class Toast(val message: String) : SettingSideEffect
    
    data object NavigateToLoginActivity : SettingSideEffect
}
