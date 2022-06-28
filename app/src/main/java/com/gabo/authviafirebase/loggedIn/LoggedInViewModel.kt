package com.gabo.authviafirebase.loggedIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabo.authviafirebase.loggedIn.model.CurrentUserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoggedInViewModel: ViewModel() {
    val userData: MutableLiveData<CurrentUserInfo?> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val isLoggedOut: MutableLiveData<Boolean> = MutableLiveData(false)


    private val snapshot = FirebaseDatabase.getInstance().reference.child("Users")
        .child(FirebaseAuth.getInstance().currentUser?.uid ?: "")
        .get()

    init {
        getProfileData()
    }
    private fun getProfileData() {
        snapshot.addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val user = dataSnapshot.getValue(CurrentUserInfo::class.java)
                userData.value = user
            }
        }.addOnFailureListener {
            error.value = "Something Went Wrong"
        }
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
        isLoggedOut.value = true
    }
}