package com.example.smartlockerui.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.integration.android.IntentIntegrator
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.smartlockerui.ui.viewmodels.UserViewModel

@Composable
fun QRCodeUnlockScreen(navController: NavController, userViewModel: UserViewModel) {

    val userId by userViewModel.userId.observeAsState()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val intent = result.data
        if (result.resultCode == android.app.Activity.RESULT_OK && intent != null) {
            val scannedResult = IntentIntegrator.parseActivityResult(result.resultCode, intent)?.contents
            if (scannedResult != null) {
                Log.d("QRCodeUnlockScreen", "Scanned QR Code: $scannedResult")
                Log.d("QRCodeUnlockScreen", "User ID: $userId")
                // Navigate to EnterLockerPINScreen with the scanned Locker ID
                navController.navigate("enterLockerPIN/$scannedResult")
            } else {
                Log.d("QRCodeUnlockScreen", "Scan canceled or failed")
            }
        }
    }

    // Automatically launch the QR scanner when the screen is opened
    LaunchedEffect(Unit) {
        val intentIntegrator = IntentIntegrator(navController.context as android.app.Activity)
        intentIntegrator.setOrientationLocked(true)
        intentIntegrator.setBeepEnabled(true)
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        intentIntegrator.setPrompt("Scan a QR Code")

        // Set to use the front camera
        intentIntegrator.setCameraId(1) // 1 typically corresponds to the front camera
        launcher.launch(intentIntegrator.createScanIntent())
    }

    // Display a back arrow and scanning message
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Back arrow at the top-left corner
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopStart)
                .padding(8.dp)
                .background(Color.DarkGray, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        // Centered scanning message
        Text(
            text = "Scanning QR Code...",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
