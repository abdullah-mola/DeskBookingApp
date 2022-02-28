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
import com.example.deskbookingappllication.model.viewModels.ProfileViewModel


class UserProfile : Fragment() {
    private var _binding: FragmentUsesrProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsesrProfileBinding.inflate(inflater, container, false)
        val userId: String = RetrofitInstance.userId.toString()
        profileViewModel.loadUser(userId)

        profileViewModel.user.observe(viewLifecycleOwner) {
            binding.etProfileEmail.text = Editable.Factory.getInstance().newEditable(it.email)
            binding.etProfileFirstname.text =
                Editable.Factory.getInstance().newEditable(it.firstname)
            binding.etProfileLastname.text = Editable.Factory.getInstance().newEditable(it.lastname)
            binding.etProfileDepartment.text =
                Editable.Factory.getInstance()
                    .newEditable(it.department)


        }




        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}