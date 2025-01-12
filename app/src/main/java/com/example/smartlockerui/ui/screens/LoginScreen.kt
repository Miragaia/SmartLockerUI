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
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.smartlockerui.ui.viewmodels.UserViewModel

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    val userIdInput = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userIdInput.value,
            onValueChange = { userIdInput.value = it },
            label = { Text("Enter User ID") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                userViewModel.setUserId(userIdInput.value) // Update UserViewModel with the entered User ID
                navController.navigate("home") // Navigate to DisplayScreen
            }
        ) {
            Text("Login")
        }
    }
}

