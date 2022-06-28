package com.gabo.authviafirebase.loggedOut.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabo.authviafirebase.databinding.FragmentRegisterStepTwoBinding
import com.gabo.authviafirebase.loggedIn.LoggedInActivity

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
                findNavController().navigateUp()
            }
            btnSignUp.setOnClickListener {
                if (binding.etUsername.text?.isEmpty() == false) {
                    viewModel.createUser(email, password)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Username shouldn't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            isCreatedSuccessfully.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            userInfo.observe(viewLifecycleOwner) { userInfo ->
                saveUserInfo(
                    binding.etUsername.text.toString(),
                    userInfo.email,
                    userInfo.password
                )
            }
            isFailure.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
            }
            isSavedSuccessfully.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(requireContext(), "Account info saved", Toast.LENGTH_SHORT)
                        .show()
                    requireActivity().finish()
                    startActivity(Intent(requireActivity(), LoggedInActivity::class.java))
                }
            }
        }
    }

}

