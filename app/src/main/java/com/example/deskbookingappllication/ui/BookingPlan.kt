package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.HORIZONTAL
import android.widget.GridLayout.VERTICAL
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deskbookingappllication.api.LoginResponse
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentBookingPlanBinding
import com.example.deskbookingappllication.model.viewModels.OfficeViewModel
import com.example.deskbookingappllication.model.viewModels.UserViewModel
import com.example.deskbookingappllication.rcadapters.RvOfficeAdapter

class BookingPlan : Fragment() {
    private val officeViewModel: OfficeViewModel by activityViewModels()
    private val userViewModel : UserViewModel by activityViewModels()
    private var _binding: FragmentBookingPlanBinding? = null
    private val binding get() = _binding!!
    private val officeAdapter:RvOfficeAdapter = RvOfficeAdapter()


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
        officeViewModel.offices.observe(viewLifecycleOwner){
            officeAdapter.swapData(it)

        }

        officeViewModel.loadoffices()
    }

    companion object {
        fun newInstance() = BookingPlan()

    }
    private fun setUpRecyclerView() = binding.rvOffice.apply {
        adapter = officeAdapter


        layoutManager = GridLayoutManager(context,2)
    }

}