package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentUserProfileBinding
import com.example.deskbookingappllication.model.User
import com.example.deskbookingappllication.model.viewModels.UserViewModel


class UserProfile : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
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
            val firstName = binding.etProfileFirstname.text.toString().trim()
            val lastName = binding.etProfileLastname.text.toString().trim()
            val email = binding.etProfileEmail.text.toString().trim()
            val password = binding.etProfilePassword.text.toString().trim()
            val department = binding.etProfileDepartment.text.toString().trim()
            user = User(email, password, firstName, lastName, department)
            userViewModel.updateUser(user)
        }

        binding.btnCommentNavigate.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.admin)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}