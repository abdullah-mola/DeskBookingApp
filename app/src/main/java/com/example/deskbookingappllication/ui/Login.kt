package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.deskbookingappllication.databinding.FragmentLoginBinding
import com.example.deskbookingappllication.model.UserViewModel
import com.example.deskbookingappllication.model.api.LoginRequestBody

class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var user: LoginRequestBody
    val userModel: UserViewModel by activityViewModels()
    lateinit var userEmail: String
    lateinit var userPassword: String
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


        return binding.root
    }

}