package com.example.deskbookingappllication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deskbookingappllication.databinding.FragmentReseverationBinding

class Reservation : Fragment() {
    private var _binding:FragmentReseverationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReseverationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return view
    }

}