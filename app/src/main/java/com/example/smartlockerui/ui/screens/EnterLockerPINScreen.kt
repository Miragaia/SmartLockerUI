package com.example.smartlockerui.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.components.NumericPad

@Composable
fun EnterLockerPINScreen(navController: NavController) {
    var lockerPIN = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter the Locker PIN:",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        NumericPad(inputState = lockerPIN)

        Button(
            onClick = { /* TODO: Validate PIN and unlock */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Unlock")
        }
    }
}
