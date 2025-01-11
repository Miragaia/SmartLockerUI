package com.example.smartlockerui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumericPad(inputState: MutableState<String>) {
    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "⌫")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        for (row in rows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (key in row) {
                    KeyButton(key = key, inputState = inputState)
                }
            }
        }
    }
}

@Composable
fun KeyButton(key: String, inputState: MutableState<String>) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .clickable {
                if (key == "⌫") {
                    inputState.value = inputState.value.dropLast(1)
                } else if (key.isNotBlank()) {
                    inputState.value += key
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = key,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

