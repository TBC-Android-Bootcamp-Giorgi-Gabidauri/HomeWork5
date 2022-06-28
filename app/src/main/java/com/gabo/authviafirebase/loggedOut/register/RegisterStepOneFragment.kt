package com.gabo.authviafirebase.loggedOut.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabo.authviafirebase.databinding.FragmentRegisterStepOneBinding

class RegisterStepOneFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStepOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnRegisterNext.setOnClickListener {
                sendDataToRegisterStepTwo(etEmail.text.toString(), etPassword.text.toString())
            }
            btnArrowBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun sendDataToRegisterStepTwo(email: String, password: String) {
        when {
            email.isEmpty() -> Toast.makeText(
                requireActivity(),
                "Email is required",
                Toast.LENGTH_SHORT
            ).show()
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Toast.makeText(
                requireActivity(),
                "Email format is wrong!",
                Toast.LENGTH_SHORT
            ).show()
            password.isEmpty() -> Toast.makeText(
                requireActivity(),
                "Password is required",
                Toast.LENGTH_SHORT
            ).show()
            password.length < 6 -> Toast.makeText(
                requireActivity(),
                "Password shouldn't be shorter than 6 characters long",
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                val direction =
                    RegisterStepOneFragmentDirections.actionRegisterStepOneFragmentToRegisterStepTwoFragment(
                        email,
                        password
                    )
                findNavController().navigate(direction)
            }
        }
    }
}