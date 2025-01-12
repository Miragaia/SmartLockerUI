package com.example.smartlockerui.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlockerui.ui.components.NumericPad
import com.example.smartlockerui.ui.viewmodels.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterLockIDScreen(navController: NavController, userViewModel: UserViewModel) {
    val lockID = remember { mutableStateOf("") }
    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()

    val userId by userViewModel.userId.observeAsState()

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
                text = "Enter the Locker ID:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            NumericPad(inputState = lockID)

            Button(
                onClick = {
                    // Validate Locker ID in Firestore
                    val lockerBoxDocument = "GnsjLzCBcaTXV1TAdTvP" // Replace with your specific document name
                    val enteredLockID = lockID.value

                    if (enteredLockID.isEmpty()) {
                        Toast.makeText(context, "Please enter a Locker ID", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (userId == null) {
                        Toast.makeText(context, "User not logged in", Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    firestore.collection("LockerBoxes")
                        .document(lockerBoxDocument)
                        .collection("Lockers") // Querying the subcollection
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            val lockerIDs = querySnapshot.documents.map { it.id } // Fetching document IDs
                            Log.d("Locker IDs", lockerIDs.toString())
                            Log.d("Entered Locker ID", enteredLockID)
                            if (lockerIDs.contains(enteredLockID)) {
                                // Navigate to EnterLockerPINScreen with the Locker ID
                                navController.navigate("enterLockerPIN/$enteredLockID")
                            } else {
                                // Show alert
                                Toast.makeText(
                                    context,
                                    "This Locker ID doesn't exist at this Locker Box",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                context,
                                "Error checking Locker ID: ${it.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Proceed")
            }
        }
    }
}
