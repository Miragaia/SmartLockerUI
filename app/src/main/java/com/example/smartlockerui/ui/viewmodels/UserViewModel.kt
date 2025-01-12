package com.example.smartlockerui.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    // LiveData for userId
    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> = _userId

    // Function to update userId
    fun setUserId(id: String) {
        _userId.value = id
    }

    // Function to clear the userId
    fun clearUserId() {
        _userId.value = null
    }
}
