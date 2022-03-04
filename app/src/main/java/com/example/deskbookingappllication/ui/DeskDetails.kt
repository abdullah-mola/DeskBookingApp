package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.databinding.FragmentDeskDetailsBinding

class DeskDetails : Fragment() {
    private var _binding: FragmentDeskDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeskDetailsBinding.inflate(inflater,container, false)


        return binding.root
    }

}