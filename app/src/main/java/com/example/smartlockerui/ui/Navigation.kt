package com.example.smartlockerui.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smartlockerui.ui.screens.DisplayScreen
import com.example.smartlockerui.ui.screens.EnterLockIDScreen
import com.example.smartlockerui.ui.screens.EnterLockerPINScreen
import com.example.smartlockerui.ui.screens.LoginScreen
import com.example.smartlockerui.ui.screens.QRCodeUnlockScreen
import com.example.smartlockerui.ui.screens.ReportErrorScreen
import com.example.smartlockerui.ui.viewmodels.UserViewModel

    @Composable
    fun Navigation(userViewModel: UserViewModel) { // Accept UserViewModel as a parameter
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(navController, userViewModel) // Add LoginScreen as the first screen
            }
            composable("home") {
                DisplayScreen(navController, userViewModel)
            }
            composable("enterLockID") {
                EnterLockIDScreen(navController, userViewModel)
            }

            composable(
                route = "enterLockerPIN/{lockerID}",
                arguments = listOf(navArgument("lockerID") { type = NavType.StringType })
            ) { backStackEntry ->
                val lockerID = backStackEntry.arguments?.getString("lockerID")
                EnterLockerPINScreen(lockerID = lockerID ?: "", navController, userViewModel)
            }
            composable("qrCodeUnlock") {
                QRCodeUnlockScreen(navController, userViewModel)
            }
            composable("reportError") {
                ReportErrorScreen(navController, userViewModel)
            }
        }
    }
