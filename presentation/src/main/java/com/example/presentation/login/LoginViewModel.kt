package com.example.presentation.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {
    fun onLoginClick() = intent {
        val id = state.id
        val pwd = state.password
        viewModelScope.launch {
            loginUseCase(id, pwd)
        }
        
    }
    
    fun onChangeId(id: String) = intent {
        reduce {
            state.copy(id = id)
        }
    }
    
    fun onChangePassword(password: String) = intent {
        reduce {
            state.copy(password = password)
        }
    }
    
    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState()
    )
}

@Immutable
data class LoginState(
    val id: String = "",
    val password: String = ""
)

sealed interface LoginSideEffect
