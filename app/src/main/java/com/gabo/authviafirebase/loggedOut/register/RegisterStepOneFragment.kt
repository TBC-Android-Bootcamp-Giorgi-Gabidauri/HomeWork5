package com.gabo.authviafirebase.loggedOut.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gabo.authviafirebase.R
import com.gabo.authviafirebase.databinding.FragmentRegisterStepOneBinding

class RegisterStepOneFragment : Fragment() {
    private lateinit var binding: FragmentRegisterStepOneBinding
    private lateinit var viewModel: RegisterStepOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterStepOneBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[RegisterStepOneViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnRegisterNext.setOnClickListener {
                sendDataToRegisterStepTwo(etEmail.text.toString(), etPassword.text.toString())
            }
            btnArrowBack.setOnClickListener {
                findNavController().navigate(R.id.action_registerStepOneFragment_to_loggedOutFragment)
            }
        }
    }

    private fun sendDataToRegisterStepTwo(email: String, password: String) {
        when {
            email.isEmpty() -> Toast.makeText(requireActivity(), "Email is required", Toast.LENGTH_SHORT).show()
            password.isEmpty() -> Toast.makeText(requireActivity(), "Password is required", Toast.LENGTH_SHORT).show()
            else -> {
                val direction = RegisterStepOneFragmentDirections.actionRegisterStepOneFragmentToRegisterStepTwoFragment(email, password)
                findNavController().navigate(direction)
            }
        }
    }
}