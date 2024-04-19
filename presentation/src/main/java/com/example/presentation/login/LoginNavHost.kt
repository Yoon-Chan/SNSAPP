package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

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
            LoginScreen {
                navController.navigate(LoginRoute.SignUpScreen.route)
            }
        }

        composable(route = LoginRoute.SignUpScreen.route) {
            SignUpScreen {
                navController.navigate(
                    route = LoginRoute.LoginScreen.route,
                    navOptions =
                        navOptions {
                            popUpTo(LoginRoute.WelcomeScreen.route)
                        },
                )
            }
        }
    }
}
