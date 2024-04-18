package com.example.presentation.login

sealed class LoginRoute(val route: String) {
    data object WelcomeScreen : LoginRoute("WelcomeScreen")

    data object LoginScreen : LoginRoute("LoginScreen")

    data object SignUpScreen : LoginRoute("SignUpScreen")
}
