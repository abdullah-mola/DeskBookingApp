package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.deskbookingappllication.databinding.FragmentBookingBinding
import com.example.deskbookingappllication.model.Book
import com.example.deskbookingappllication.model.viewModels.BookingViewModel

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val bookingViewModel: BookingViewModel by activityViewModels()
    private lateinit var book: Book
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)



        return binding.root
    }

}