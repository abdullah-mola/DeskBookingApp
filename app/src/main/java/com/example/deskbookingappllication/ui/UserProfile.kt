package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentUsesrProfileBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel


class UserProfile : Fragment() {
    private var _binding: FragmentUsesrProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsesrProfileBinding.inflate(inflater, container, false)
        val userId: String = RetrofitInstance.userId.toString()
        userViewModel.loadUser(userId)

        userViewModel.user.observe(viewLifecycleOwner) {
             binding.etProfileEmail.text = Editable.Factory.getInstance().newEditable(it.email)
            binding.etProfileFirstname.text =
                Editable.Factory.getInstance().newEditable(it.firstname)
            binding.etProfileLastname.text = Editable.Factory.getInstance().newEditable(it.lastname)
            binding.etProfileDepartment.text =
                Editable.Factory.getInstance()
                    .newEditable(it.department)


        }

       binding.btnProfileSave.setOnClickListener {
           val firstName = binding.etProfileFirstname.getText().toString().trim()
           val lastName = binding.etProfileLastname.getText().toString().trim()
           val email = binding.etProfileEmail.getText().toString().trim()
           val password = binding.etProfilePassword.getText().toString().trim()
           val department = binding.etProfileDepartment.getText().toString().trim()
           user = User(email,password,firstName,lastName,department)

           userViewModel.updateUser(user)

       }




        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}