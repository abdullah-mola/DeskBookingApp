package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deskbookingappllication.databinding.FragmentOfficesBinding
import com.example.deskbookingappllication.rcadapters.RvOfficeAdapter
import com.example.deskbookingappllication.viewModels.OfficeViewModel
import com.google.gson.Gson

class OfficesFragment : Fragment() {
    private val officeViewModel: OfficeViewModel by activityViewModels()
    private var _binding: FragmentOfficesBinding? = null
    private val binding get() = _binding!!
    private val officeAdapter: RvOfficeAdapter = RvOfficeAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentOfficesBinding.inflate(inflater, container, false)
        setActivityTitle("Offices")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()

        officeViewModel.officeList.observe(viewLifecycleOwner) {
            officeAdapter.swapData(it)
        }
        officeViewModel.loadOffices()
        officeAdapter.click {
            Navigation.findNavController(binding.root).navigate(
                OfficesFragmentDirections.actionBookingPlanToDesks(
                    Gson().toJson(it)
                )
            )

        }


    }


    private fun setUpRecyclerView() = binding.rvOffice.apply {
        adapter = officeAdapter


        layoutManager = LinearLayoutManager(context)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}