package com.example.smartlockerui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.smartlockerui.ui.theme.SmartLockerUITheme
import com.example.smartlockerui.ui.Navigation
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.Composable
import com.example.smartlockerui.ui.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContent {
            SmartLockerUITheme {
                Navigation(userViewModel)
            }
        }
    }
}