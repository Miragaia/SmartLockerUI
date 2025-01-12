package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.viewmodels.UserViewModel

@Composable
fun ReportErrorScreen(navController: NavController, userViewModel: UserViewModel) {
    // State variable to hold the user's input
    var errorMessage by remember { mutableStateOf("") }
    val userId by userViewModel.userId.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Report an Error",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Describe the issue you're experiencing:",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = errorMessage,
                onValueChange = { errorMessage = it }, // Update the state variable
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter your message") },
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                // Handle the error reporting logic, e.g., send to Firestore
                println("Error reported by userId: $userId")
                println("Error message: $errorMessage")
                navController.navigateUp()
            }) {
                Text("Submit")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigateUp() }) {
                Text("Back")
            }
        }
    }
}

