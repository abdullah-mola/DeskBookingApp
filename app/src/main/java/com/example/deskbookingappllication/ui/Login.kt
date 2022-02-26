package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.databinding.FragmentLoginBinding

import com.example.deskbookingappllication.model.UserViewModel
import com.example.deskbookingappllication.model.api.LoginRequestBody
import kotlinx.android.synthetic.main.fragment_usesr_profile.*

class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: LoginRequestBody
    private val userModel: UserViewModel by activityViewModels()
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener {
            userEmail = binding.etLoginEmail.text.toString()
            userPassword = binding.etLoginPassword.text.toString()
            user = LoginRequestBody(userEmail, userPassword)
            userModel.login(user)

        }
        binding.btnRegister.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(LoginDirections.actionLoginToRegister())
        }
            binding.btnLogin.setOnClickListener {
                userEmail = binding.etLoginEmail.text.toString()
                userPassword = binding.etLoginPassword.text.toString()
                user= LoginRequestBody(userEmail,userPassword)

                NavHostFragment.findNavController(this)
                    .navigate(LoginDirections.actionLoginToBookingPlan())
                val loginreq =userModel.login(user)


            }

        return binding.root
    }


}
