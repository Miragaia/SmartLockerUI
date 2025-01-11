package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.components.NumericPad
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterLockIDScreen(navController: NavController) {
    val lockID = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Set the background color to black
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter the Lock ID:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Ensure the text is visible against the dark background
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the entered Lock ID
            TextField(
                value = lockID.value,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.DarkGray,
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Set text color explicitly
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dark-themed Numeric Pad
            NumericPad(inputState = lockID)

            Button(
                onClick = { navController.navigate("enterLockerPIN") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Proceed")
            }
        }
    }
}
