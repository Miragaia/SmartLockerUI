package com.example.smartlockerui.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.smartlockerui.ui.components.NumericPad
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterLockerPINScreen(lockerID: String, navController: NavController) {
    val pin = remember { mutableStateOf("") }
    val firestore = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter the PIN for Locker ID: $lockerID",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = pin.value,
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
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            NumericPad(inputState = pin)

            Button(
                onClick = {
                    val lockerBoxDocument = "GnsjLzCBcaTXV1TAdTvP"
                    // Fetch the accessCode for the lockerID from Firestore
                    firestore.collection("LockerBoxes")
                        .document(lockerBoxDocument)
                        .collection("Lockers")
                        .document(lockerID)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                Log.d("EnterLockerPINScreen", "DocumentSnapshot data: ${document.data}")
                                val accessCode = document.getString("accessCode")
                                Log.d("EnterLockerPINScreen", "Access Code: $accessCode")
                                if (accessCode == pin.value) {
                                    // PIN is correct, navigate or unlock
                                    Toast.makeText(
                                        context,
                                        "Locker unlocked successfully!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    // Add navigation or unlocking logic here
                                } else {
                                    // PIN is incorrect
                                    Toast.makeText(
                                        context,
                                        "Incorrect PIN. Please try again.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                // Locker ID doesn't exist
                                Toast.makeText(
                                    context,
                                    "Locker ID not found.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        .addOnFailureListener { exception ->
                            // Error fetching document
                            Toast.makeText(
                                context,
                                "Error accessing Firestore: ${exception.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Unlock")
            }
        }
    }
}

