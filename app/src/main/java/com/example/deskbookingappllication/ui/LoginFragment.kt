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
import com.example.deskbookingappllication.model.viewModels.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: LoginRequestBody
    private val userModel: UserViewModel by activityViewModels()
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var token:String
    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = activity?.getSharedPreferences("Login", Context.MODE_PRIVATE)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setActivityTitle("Login")
        userModel.userLoginData.observe(viewLifecycleOwner) {
             token = it.token
            val userId = it.userId
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
                        if(binding.loginCheckBox.isChecked){
                            RetrofitInstance.authToken = token
                            preferences?.edit()?.putString("TOKEN",token)?.apply()
                        }
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.offices)
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


    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}