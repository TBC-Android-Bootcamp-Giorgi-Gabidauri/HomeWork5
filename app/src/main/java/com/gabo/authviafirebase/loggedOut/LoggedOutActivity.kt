package com.gabo.authviafirebase.loggedOut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.gabo.authviafirebase.R
import com.gabo.authviafirebase.loggedOut.logIn.LogInFragment

class LoggedOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_out)

    }
}