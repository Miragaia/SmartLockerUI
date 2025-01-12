package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.components.UnlockOption
import com.example.smartlockerui.ui.viewmodels.UserViewModel

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black // Set dark background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main content
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Choose a login method:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    UnlockOption(text = "User ID") {
                        navController.navigate("loginWithUserId")
                    }
                    UnlockOption(text = "QR Code") {
                        navController.navigate("loginWithQrCode")
                    }
                }
            }
        }
    }
}
