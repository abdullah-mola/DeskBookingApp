package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentDeskDetailsBinding
import com.example.deskbookingappllication.model.Desk
import com.example.deskbookingappllication.model.viewModels.DeskViewModel
import com.example.deskbookingappllication.rcadapters.RvDeskDetailsAdapter
import com.google.gson.Gson

class DeskDetails : Fragment() {
    private var _binding: FragmentDeskDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DeskDetailsArgs by navArgs()
    private val deskDetailsAdapter: RvDeskDetailsAdapter = RvDeskDetailsAdapter()
    private val deskViewModel: DeskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeskDetailsBinding.inflate(inflater, container, false)
        setActivityTitle("Desk Equipments")



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argsId = args.id
        val desk  = Gson().fromJson(argsId,Desk::class.java)

        deskViewModel.getDeskDetails(desk.desk_id)
        deskViewModel.deskDetails.observe(viewLifecycleOwner){

            setUpRecyclerView()
            deskDetailsAdapter.swapData(it)



        }


    }

    private fun setUpRecyclerView() = binding.rvEquipments.apply {
        adapter = deskDetailsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

}