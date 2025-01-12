package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.components.UnlockOption
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import com.example.smartlockerui.ui.viewmodels.UserViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun DisplayScreen(navController: NavController, userViewModel: UserViewModel) {
    // Collect userId from ViewModel
    val userId = userViewModel.userId.observeAsState()

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
                    text = "Welcome, ${userId.value ?: "Guest"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Unlock with:",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    UnlockOption(text = "PIN") {
                        navController.navigate("enterLockID")
                    }
                    UnlockOption(text = "QR Code") {
                        navController.navigate("qrCodeUnlock")
                    }
                }
            }

            Button(
                onClick = {
                    userViewModel.clearUserId() // Clear user ID
                    navController.navigate("login") { popUpTo(0) } // Navigate to login screen
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text("Logout", fontSize = 14.sp)
            }

            // Error button
            Button(
                onClick = { navController.navigate("reportError") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(4.dp)
            ) {
                Text("Report", fontSize = 12.sp)
            }

        }
    }
}

