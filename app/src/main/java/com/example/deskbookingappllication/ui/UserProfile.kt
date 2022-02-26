package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.FragmentUsesrProfileBinding
import kotlinx.android.synthetic.main.fragment_usesr_profile.*


class UserProfile : Fragment() {
    private var _binding: FragmentUsesrProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsesrProfileBinding.inflate(inflater, container, false)



   return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (R.layout.fragment_usesr_profile)

    }


    fun viewInitializations() {
        // To show back button in actionbar


    }

    // Checking if the input in form is valid
    private fun validateInput(): Boolean {
        if (et_first_name.text.toString().equals("")) {
            et_first_name.error = "Please Enter First Name"
            return false
        }
        if (et_last_name.text.toString().equals("")) {
            et_last_name.error = "Please Enter Last Name"
            return false
        }
        if (et_email.text.toString().equals("")) {
            et_email.error = "Please Enter Email"
            return false
        }

        if (et_contact_no.text.toString().equals("")) {
            et_contact_no.error = "Please Enter Contact No"
            return false
        }
        if (et_des.text.toString().equals("")) {
            et_des.error = "Please Enter Designation"
            return false
        }
        // checking the proper email format
        if (!isEmailValid(et_email.text.toString())) {
            et_email.error = "Please Enter Valid Email"
            return false
        }

        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}