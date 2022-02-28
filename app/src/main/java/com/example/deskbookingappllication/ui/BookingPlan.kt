package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deskbookingappllication.databinding.FragmentBookingPlanBinding
import com.example.deskbookingappllication.model.viewModels.OfficeViewModel
import com.example.deskbookingappllication.rcadapters.RvOfficeAdapter

class BookingPlan : Fragment() {
    private val officeViewModel: OfficeViewModel by activityViewModels()
    private var _binding: FragmentBookingPlanBinding? = null
    private val binding get() = _binding!!
    private val officeAdapter: RvOfficeAdapter = RvOfficeAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingPlanBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()
        officeViewModel.offices.observe(viewLifecycleOwner) {
            officeAdapter.swapData(it)

        }
        officeViewModel.loadOffices()

    }


    private fun setUpRecyclerView() = binding.rvOffice.apply {
        adapter = officeAdapter


        layoutManager = LinearLayoutManager(context)
    }

}