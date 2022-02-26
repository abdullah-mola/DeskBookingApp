package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.deskbookingappllication.databinding.FragmentBookingPlanBinding
import com.example.deskbookingappllication.model.OfficeViewModel

class BookingPlan : Fragment() {
    private val officeViewModel: OfficeViewModel by activityViewModels()
    private var _binding: FragmentBookingPlanBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingPlanBinding.inflate(inflater, container, false)


        return binding.root

    }

    companion object {
        fun newInstance() = BookingPlan()
    }

}