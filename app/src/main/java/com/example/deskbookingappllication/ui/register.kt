package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.deskbookingappllication.databinding.FragmentRegisterBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel


class Register : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener {
            val firstName = binding.txtInputEditTextFirstname.text.toString()
            val lastName = binding.txtInputEditTextLastname.text.toString()
            val email = binding.etLoginEmail.text.toString()
            val department = binding.etDepartment.text.toString()
            val password = binding.etLoginPassword.text.toString()
            val user: User = User(email, password, firstName, lastName, department)
            userViewModel.register(user)

        }
        return binding.root
    }
}