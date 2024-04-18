package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.route,
    ) {
        composable(route = LoginRoute.WelcomeScreen.route) {
            WelcomeScreen {
                navController.navigate(LoginRoute.LoginScreen.route)
            }
        }

        composable(route = LoginRoute.LoginScreen.route) {
            LoginScreen()
        }

        composable(route = LoginRoute.SignUpScreen.route) {
            SignUpScreen(id = "", username = "", password1 = "", password2 = "", {}, {}, {}, {}, {})
        }
    }
}
