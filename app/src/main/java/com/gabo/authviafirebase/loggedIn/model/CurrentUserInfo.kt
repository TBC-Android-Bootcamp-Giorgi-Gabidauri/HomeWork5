package com.gabo.authviafirebase.loggedIn.model

data class CurrentUserInfo(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val uid: String = ""
)