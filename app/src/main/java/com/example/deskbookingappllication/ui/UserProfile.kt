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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}