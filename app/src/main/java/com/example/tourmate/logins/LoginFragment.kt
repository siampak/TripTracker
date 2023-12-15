package com.example.tourmate.logins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.tourmate.R
import com.example.tourmate.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding  //data binding
    private val loginViewModel: LoginViewModel by viewModels() // call viewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        loginViewModel.authStatusLiveData.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.AuthenticationStatus.AUTENTETICATED) {

                findNavController().navigate(R.id.login_to_home_action)  //login click authenticated --> Home fragment
            }
        }

        //error massage observe live toast
        loginViewModel.errMsgLiveData.observe(viewLifecycleOwner) {
            binding.errMsgTvId.text = it
        }


        //login button click check
        binding.loginbtnId.setOnClickListener{

            loginViewModel.login(binding.emailId.text.toString(),binding.passwordId.text.toString())

        }


        //signup button click check
        binding.signbtnId.setOnClickListener{

            loginViewModel.register(binding.emailId.text.toString(),binding.passwordId.text.toString())

        }



        return binding.root

    }

}


