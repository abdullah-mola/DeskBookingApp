package com.example.deskbookingappllication.ui

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
import com.example.deskbookingappllication.databinding.FragmentRegisterBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel

class RegisterFragment : Fragment() {
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
            val firstName = binding.txtInputEditTextFirstname.text.toString().trim()
            val lastName = binding.txtInputEditTextLastname.text.toString().trim()
            val email = binding.etLoginEmail.text.toString().trim()
            val department = binding.etDepartment.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()
            user = User(email, password, firstName, lastName, department,null)
            userViewModel.register(user)

            userViewModel.registerStatusCode.observe(viewLifecycleOwner) {
                when (it) {
                    204 -> {
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.login)
                        Toast.makeText(context, "Signed up Successfully", Toast.LENGTH_LONG).show()
                    }
                    409 -> {
                        Toast.makeText(
                            context,
                            "Email already registered", Toast.LENGTH_LONG
                        ).show()
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
    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

}