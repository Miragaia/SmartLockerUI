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

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { DisplayScreen(navController) }
        composable("enterLockID") { EnterLockIDScreen(navController) }

        // Define the argument for the enterLockerPIN route
        composable(
            route = "enterLockerPIN/{lockerID}",
            arguments = listOf(navArgument("lockerID") { type = NavType.StringType })
        ) { backStackEntry ->
            val lockerID = backStackEntry.arguments?.getString("lockerID")
            EnterLockerPINScreen(lockerID = lockerID ?: "", navController)
        }
    }
}

