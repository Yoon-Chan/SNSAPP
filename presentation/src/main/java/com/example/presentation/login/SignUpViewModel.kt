package com.example.presentation.login

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.login.SignUpUseCase
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
class SignUpViewModel
    @Inject
    constructor(
        private val signUpUseCase: SignUpUseCase,
    ) : ViewModel(), ContainerHost<SignUpState, SignUpSideEffect> {
        override val container: Container<SignUpState, SignUpSideEffect> =
            container(
                initialState = SignUpState(),
                buildSettings = {
                    this.exceptionHandler =
                        CoroutineExceptionHandler { coroutineContext, throwable ->
                            intent {
                                postSideEffect(SignUpSideEffect.Toast(throwable.message ?: ""))
                            }
                        }
                },
            )

        fun onIdChange(id: String) =
            blockingIntent {
                reduce { state.copy(id = id) }
            }

        fun onUserChange(user: String) =
            blockingIntent {
                reduce { state.copy(user = user) }
            }

        fun onPwdChange(password: String) =
            blockingIntent {
                reduce { state.copy(password = password) }
            }

        fun onRepeatPwdChange(repeatPassword: String) =
            blockingIntent {
                reduce { state.copy(repeatPassword = repeatPassword) }
            }

        fun onSignUpClick() =
            intent {
                if (state.password == state.repeatPassword) {
                    val isSuccessful = signUpUseCase(id = state.id, username = state.user, password = state.password).getOrThrow()
                    if (isSuccessful) {
                        postSideEffect(SignUpSideEffect.NavigateToLoginScreen)
                        postSideEffect(SignUpSideEffect.Toast("회원가입에 성공했습니다."))
                    }
                } else {
                    postSideEffect(SignUpSideEffect.Toast(message = "패스워드를 다시 확인해주세요."))
                    return@intent
                }
            }
    }

data class SignUpState(
    val id: String = "",
    val user: String = "",
    val password: String = "",
    val repeatPassword: String = "",
)

sealed interface SignUpSideEffect {
    class Toast(val message: String) : SignUpSideEffect

    data object NavigateToLoginScreen : SignUpSideEffect
}
