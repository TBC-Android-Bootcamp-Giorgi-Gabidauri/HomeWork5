package com.gabo.authviafirebase.loggedOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabo.authviafirebase.R
import com.gabo.authviafirebase.databinding.FragmentLoggedOutBinding

class LoggedOutFragment : Fragment() {
    private lateinit var binding: FragmentLoggedOutBinding
    private lateinit var viewModel: LoggedOutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoggedOutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoggedOutViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            val direction = LoggedOutFragmentDirections.actionLoggedOutFragmentToLogInFragment()
            findNavController().navigate(direction)
        }
        binding.btnRegister.setOnClickListener {
            val direction =
                LoggedOutFragmentDirections.actionLoggedOutFragmentToRegisterStepOneFragment()
            findNavController().navigate(direction)
        }
    }

}