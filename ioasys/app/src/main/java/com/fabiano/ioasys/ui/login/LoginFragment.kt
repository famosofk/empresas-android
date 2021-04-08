package com.fabiano.ioasys.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.fabiano.ioasys.R
import com.fabiano.ioasys.databinding.LoginFragmentBinding
import com.fabiano.ioasys.model.User
import com.fabiano.ioasys.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initializeObservers()
        initializeClickListeners()
        return binding.root
    }

    private fun initializeClickListeners() {
        binding.button.setOnClickListener {
            viewModel.login(
                User(
                    binding.emailField.text.toString().trim(),
                    binding.passwordField.text.toString().trim()
                )
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeObservers() {
        viewModel.sucessfulLogin.observe(viewLifecycleOwner) {
            binding.errorMessage.setText(viewModel.loginError)
            if (it) {
                val header = viewModel.provideHeaderData()
                val bundle = Bundle()
                bundle.putSerializable(Constants.HEADER_DATA_KEY, header)
                binding.root.findNavController()
                    .navigate(R.id.action_loginFragment_to_searchFragment, bundle)
            }
        }

        viewModel.inProgressLogin.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingScreen.visibility = View.VISIBLE
                binding.loadingScreen.setOnTouchListener(null)
            } else {
                binding.loadingScreen.visibility = View.GONE
            }
        }
    }

}