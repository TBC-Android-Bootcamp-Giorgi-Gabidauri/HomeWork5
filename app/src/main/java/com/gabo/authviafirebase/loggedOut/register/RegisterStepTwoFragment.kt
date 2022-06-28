package com.gabo.authviafirebase.loggedOut.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabo.authviafirebase.R
import com.gabo.authviafirebase.databinding.FragmentRegisterStepTwoBinding

class RegisterStepTwoFragment : Fragment() {

    private lateinit var binding: FragmentRegisterStepTwoBinding
    private lateinit var viewModel: RegisterStepTwoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterStepTwoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterStepTwoViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: RegisterStepTwoFragmentArgs by navArgs()
        val email = args.email
        val password = args.password
        with(binding) {
            btnArrowBack.setOnClickListener {
                findNavController().navigate(R.id.action_registerStepTwoFragment_to_registerStepOneFragment)
            }
            btnSignUp.setOnClickListener {
                with(viewModel){
                    createUser(email, password)
                }
            }
        }
        setupObservers()

    }
    private fun setupObservers(){
        with(viewModel){
            isCreatedSuccessfully.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(requireContext(), "Account Created", Toast.LENGTH_SHORT).show()
                }
            }
            isFailure.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
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
    }

}

