package com.example.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.login.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val loginUseCase: LoginUseCase,
        private val setTokenUseCase: SetTokenUseCase,
    ) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {
        override val container: Container<LoginState, LoginSideEffect> =
            container(
                initialState = LoginState(),
                buildSettings = {
                    this.exceptionHandler =
                        CoroutineExceptionHandler { _, throwable ->
                            intent {
                                postSideEffect(LoginSideEffect.Toast(message = throwable.message ?: ""))
                            }
                        }
                },
            )

        fun onLoginClick() =
            intent {
                val id = state.id
                val pwd = state.password
                val token: String = loginUseCase(id, pwd).getOrThrow()
                setTokenUseCase(token)
//                 Log.e("LoginViewModel", "$token")
//                 postSideEffect(LoginSideEffect.Toast(message = "token = $token"))
                postSideEffect(LoginSideEffect.NavigateToMainActivity)
            }

        fun onChangeId(id: String) =
            blockingIntent {
                reduce {
                    state.copy(id = id)
                }
            }

        fun onChangePassword(password: String) =
            blockingIntent {
                reduce {
                    state.copy(password = password)
                }
            }
    }

@Immutable
data class LoginState(
    val id: String = "",
    val password: String = "",
)

sealed interface LoginSideEffect {
    class Toast(val message: String) : LoginSideEffect

    data object NavigateToMainActivity : LoginSideEffect
}
