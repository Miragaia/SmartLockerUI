package com.example.smartlockerui.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.functions.functions

class UserViewModel : ViewModel() {

    companion object {
        const val TAG = "UserViewModel"
        const val CLOUD_FUNCTION_UNLOCK_LOCKER = "unlock_locker"
    }

    private val cloudFunctions = Firebase.functions

    // LiveData for userId
    private val _userToken = MutableLiveData<String?>()
    val userToken: LiveData<String?> = _userToken

    // Function to update userId
    fun setUserToken(id: String) {
        _userToken.value = id
    }

    // Function to clear the userId
    fun clearUserToken() {
        _userToken.value = null
    }

    fun lockLocker(lockerBoxId: String, lockerId: String, accessCode: String, onResult: (Boolean) -> Unit) {
        cloudFunctions
            .getHttpsCallable(CLOUD_FUNCTION_UNLOCK_LOCKER)
            .call(hashMapOf(
                "lockerBoxId" to lockerBoxId,
                "lockerId" to lockerId,
                "accessCode" to accessCode,
                "accessToken" to _userToken.value
            ))
            .continueWith { task ->
                Log.d(TAG, "UnLockLocker ContinueWith Result: ${task.result.getData()}")
            }
            .addOnSuccessListener { result ->
                Log.d(TAG, "UnLockLocker SuccessListener: $result")
                onResult(true)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "UnLockLocker FailureListener: $e")
                onResult(false)
            }
    }


}
