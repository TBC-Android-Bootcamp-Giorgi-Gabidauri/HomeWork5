package com.gabo.authviafirebase.loggedOut.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabo.authviafirebase.loggedOut.register.model.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterStepTwoViewModel : ViewModel() {
    val userInfo: MutableLiveData<UserInfo> = MutableLiveData()
    val isSavedSuccessfully: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isFailure: MutableLiveData<String> = MutableLiveData()
    val isCreatedSuccessfully: MutableLiveData<Boolean> = MutableLiveData()

    fun createUser(email: String, password: String) {
        isLoading.value = true
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                isLoading.value = false
                isCreatedSuccessfully.value = true
                userInfo.value = UserInfo(email, password)
            } else {
                isLoading.value = false
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
                    isSavedSuccessfully.value = true
                }
            }.addOnFailureListener {
                isFailure.value = it.message
            }
    }
}