package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deskbookingappllication.R
import com.example.deskbookingappllication.api.RetrofitInstance
import com.example.deskbookingappllication.databinding.FragmentDesksBinding
import com.example.deskbookingappllication.model.Office
import com.example.deskbookingappllication.model.viewModels.DeskViewModel
import com.example.deskbookingappllication.rcadapters.RvDeskAdapter
import com.google.gson.Gson

class Desks : Fragment() {
    private val deskViewModel: DeskViewModel by activityViewModels()
    private val args:DesksArgs by navArgs<DesksArgs>()
    private val deskAdapter: RvDeskAdapter = RvDeskAdapter()
    private var _binding: FragmentDesksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDesksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idString = args.id
        val officeIdString = Gson().fromJson(idString,Office::class.java)

        setUpRecyclerView()
        deskViewModel.desks.observe(viewLifecycleOwner) {
            deskAdapter.swapData(it)

        }

        deskViewModel.loadDesksByOfficeid(officeIdString.office_id)
        deskAdapter.click{
            Navigation.findNavController(binding.root).navigate(R.id.offices)
        }

    }

    private fun setUpRecyclerView() = binding.rvDesks.apply {
        adapter = deskAdapter
        layoutManager = GridLayoutManager(context, 2)
    }
    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}