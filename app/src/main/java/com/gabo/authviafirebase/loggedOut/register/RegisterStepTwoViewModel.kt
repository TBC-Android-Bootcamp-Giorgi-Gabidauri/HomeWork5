package com.gabo.authviafirebase.loggedOut.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterStepTwoViewModel : ViewModel() {
    val isCreatedSuccessfully: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSavedSucessfully: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isFailure: MutableLiveData<String> = MutableLiveData()

    fun createUser(email: String, password: String) {
        isLoading.value = true
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                isLoading.value = false
                isCreatedSuccessfully.value = true
            } else {
                isFailure.value = task.exception?.message
            }

        }
    }

    fun saveUserInfo(userName: String, email: String, password: String) {
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        val userReference: DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String, Any>()
        userMap["uid"] = currentUserId
        userMap["username"] = userName
        userMap["email"] = email
        userMap["password"] = password

        userReference.child(currentUserId).setValue(userMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isSavedSucessfully.value = true
                }
            }
    }
}