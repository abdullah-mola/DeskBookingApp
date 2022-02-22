package com.example.deskbookingappllication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deskbookingappllication.databinding.FragmentFavorietBinding

class Favorites : Fragment() {
    private var _binding:FragmentFavorietBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavorietBinding.inflate(inflater, container, false)



        return view
    }

}