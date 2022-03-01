package com.example.deskbookingappllication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.FragmentLoginBinding
import com.example.deskbookingappllication.model.viewModels.UserViewModel
import com.example.deskbookingappllication.api.LoginRequestBody
import com.example.deskbookingappllication.api.RetrofitInstance

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
    ): View {
        userModel.userLoginData.observe(viewLifecycleOwner){
            val token = it.token
            val userId = it.userId
            RetrofitInstance.authToken = token
            RetrofitInstance.userId = userId

        }
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener {
            userEmail = binding.etLoginEmail.text.toString()
            userPassword = binding.etLoginPassword.text.toString()
            user = LoginRequestBody(userEmail, userPassword)
            userModel.login(user)
            userModel.statusCode.observe(viewLifecycleOwner) {
                when (it) {
                    200 -> {
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.bookingPlan)
                        Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_LONG).show()
                    }
                    401 -> {
                        Toast.makeText(
                            context,
                            "Please make sure that the entered user E-mail & Password are Correct",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }


        }
        binding.btnRegister.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.register)
        }
        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}