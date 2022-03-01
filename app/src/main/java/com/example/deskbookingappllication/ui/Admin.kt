package com.example.deskbookingappllication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deskbookingappllication.databinding.FragmentAdminBinding
import com.example.deskbookingappllication.model.viewModels.AdminViewModel
import com.example.deskbookingappllication.rcadapters.RvCommentAdapter

class Admin : Fragment() {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val commentAdapter: RvCommentAdapter = RvCommentAdapter()
    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        adminViewModel.comment.observe(viewLifecycleOwner) {
            commentAdapter.swapData(it)
        }
        adminViewModel.loadComments()
    }

    private fun setUpRecyclerView() = binding.rvComment.apply {
        adapter = commentAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}