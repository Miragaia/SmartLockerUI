package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.viewmodels.UserViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun LoginWithQrCodeScreen(navController: NavController, userViewModel: UserViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Scan QR Code to Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Replace with actual QR code scanner logic
            Button(
                onClick = {
                    // Simulate setting a user ID after QR code scan
                    userViewModel.setUserId("QRUser123")
                    navController.navigate("home")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Simulate QR Code Login")
            }
        }
    }
}
