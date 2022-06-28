package com.gabo.authviafirebase.loggedIn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gabo.authviafirebase.databinding.ActivityLoggedInBinding
import com.gabo.authviafirebase.loggedOut.LoggedOutActivity

class LoggedInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoggedInBinding
    private lateinit var viewModel: LoggedInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoggedInViewModel::class.java]
        setupObservers()
        binding.btnLogOut.setOnClickListener {
            viewModel.logOut()
        }
    }
    private fun setupObservers(){
        with(viewModel){
            userData.observe(this@LoggedInActivity){
            binding.tvWelcome.text = "Welcome ${it?.username}"
        }
            isLoggedOut.observe(this@LoggedInActivity){
                if(it){
                    finish()
                    startActivity(Intent(this@LoggedInActivity, LoggedOutActivity::class.java))
                }
            }
        }
    }

}