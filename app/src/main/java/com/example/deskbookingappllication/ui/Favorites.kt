package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deskbookingappllication.databinding.FragmentFavorietBinding
import com.example.deskbookingappllication.model.viewModels.DeskViewModel
import com.example.deskbookingappllication.rcadapters.RvDeskAdapter

class Favorites : Fragment() {
    private val deskViewModel: DeskViewModel by activityViewModels()
    private val deskAdapter: RvDeskAdapter = RvDeskAdapter()
    private var _binding: FragmentFavorietBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavorietBinding.inflate(inflater, container, false)


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        deskViewModel.desks.observe(viewLifecycleOwner) {
            deskAdapter.swapData(it)
        }


        deskViewModel.loadFavouriteDesks()
        //deskViewModel.loadDesks()

    }

    private fun setUpRecyclerView() = binding.rvFavourite.apply {
        adapter = deskAdapter
        layoutManager = GridLayoutManager(context, 2)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}