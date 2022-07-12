package com.example.deskbookingappllication.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.api.LoginRequestBody
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentLoginBinding
import com.example.deskbookingappllication.viewModels.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: LoginRequestBody
    private val userModel: UserViewModel by activityViewModels()
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private var token = ""
    private var userId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val preferences: SharedPreferences? =
            activity?.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        setActivityTitle("Login")



        binding.btnLogin.setOnClickListener {
            userEmail = binding.etLoginEmail.text.toString()
            userPassword = binding.etLoginPassword.text.toString()
            user = LoginRequestBody(userEmail, userPassword)
            userModel.login(user)
            userModel.userLoginData.observe(viewLifecycleOwner) {
                token = it.token
                userId = it.userId
                RetrofitInstance.userId = userId


            }
            userModel.statusCode.observe(viewLifecycleOwner) {
                when (it) {
                    200 -> {
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.offices)

                        with(preferences?.edit()) {
                            this?.putString("token", token)
                            this?.putString("userId", userId)
                            this?.apply()
                        }
                        RetrofitInstance.authToken = preferences!!.getString("token", "").toString()
                        RetrofitInstance.userId = preferences.getString("userId", "").toString()

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
        if (preferences!!.getString("token", "") != "") {
            RetrofitInstance.authToken = preferences.getString("token", "").toString()
            RetrofitInstance.userId = preferences.getString("userId", "").toString()
            NavHostFragment.findNavController(this)
                .navigate(R.id.offices)
        }



        binding.btnRegister.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.register)
        }
        return binding.root
    }


    private fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}