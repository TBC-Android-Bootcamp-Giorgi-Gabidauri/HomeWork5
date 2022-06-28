package com.gabo.authviafirebase.loggedOut.logIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LogInViewModel : ViewModel() {
    val isSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val currentUser: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isFailure: MutableLiveData<String> = MutableLiveData()
    fun loginUser(email: String, password: String) {
        isLoading.value = true
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                isSuccessful.value = true
                isLoading.value = false
            }else{
                isFailure.value = it.exception?.message
                isLoading.value = false
            }
        }
    }
    fun isLoggedIn(){
            currentUser.value = FirebaseAuth.getInstance().currentUser != null
    }
}