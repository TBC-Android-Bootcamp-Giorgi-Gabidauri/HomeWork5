package com.gabo.authviafirebase.loggedOut.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabo.authviafirebase.R
import com.gabo.authviafirebase.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[LogInViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnArrowBack.setOnClickListener {
                findNavController().navigate(R.id.action_logInFragment_to_loggedOutFragment)
            }
            btnLogIn.setOnClickListener {
                loginUser()
            }

        }
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
            }
            isSuccessful.observe(viewLifecycleOwner) {
                Toast.makeText(requireActivity(), "Succesfull", Toast.LENGTH_SHORT).show()
            }
            isFailure.observe(viewLifecycleOwner) {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        when {
            email.isEmpty() -> Toast.makeText(
                requireActivity(),
                "email is required",
                Toast.LENGTH_SHORT
            ).show()
            password.isEmpty() -> Toast.makeText(
                requireActivity(),
                "Password is required",
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                viewModel.loginUser(email, password)
            }
        }
    }

}