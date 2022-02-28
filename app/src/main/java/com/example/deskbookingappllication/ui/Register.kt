package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.databinding.FragmentRegisterBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel

class Register : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)



        binding.btnSignup.setOnClickListener {
            val firstName = binding.txtInputEditTextFirstname.getText().toString().trim()
            val lastName = binding.txtInputEditTextLastname.getText().toString().trim()
            val email = binding.etLoginEmail.getText().toString().trim()
            val department = binding.etDepartment.getText().toString().trim()
            val password = binding.etLoginPassword.getText().toString().trim()
            user = User(email, password, firstName, lastName, department)
            userViewModel.register(user)

            userViewModel.registerstatusCode.observe(viewLifecycleOwner) {
                when (it) {
                    204 -> {
                        NavHostFragment.findNavController(this)
                            .navigate(RegisterDirections.actionRegisterToLogin())
                        Toast.makeText(context, "Signed up Successfully", Toast.LENGTH_LONG).show()
                    }
                    409 -> {
                        Toast.makeText(context,
                                "Email already registered",Toast.LENGTH_LONG).show()
                    }
                    400 -> {
                        Toast.makeText(
                            context,
                            "Please make sure that you filled all fields",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }


        }
        return binding.root
    }
}