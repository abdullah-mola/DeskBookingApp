package com.example.deskbookingappllication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deskbookingappllication.databinding.FragmentAdminBinding
import com.example.deskbookingappllication.model.viewModels.AdminViewModel
import com.example.deskbookingappllication.rcadapters.RvCommentAdapter
import com.example.deskbookingappllication.rcadapters.RvDeskAdapter

class AdminFragment : Fragment() {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val commentAdapter: RvCommentAdapter = RvCommentAdapter()
    private var _binding: FragmentAdminBinding? = null
    private val deskAdapter: RvDeskAdapter = RvDeskAdapter()
    private val binding get() = _binding!!
    private lateinit var id :String

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
        val deskId = deskAdapter.currentItem?.desk_id


        if (deskId != null) {
            adminViewModel.loadComments(deskId)
        }else{
            Log.d("AdminFragment","desk id is null")
        }
    }

    private fun setUpRecyclerView() = binding.rvComment.apply {
        adapter = commentAdapter
        layoutManager = LinearLayoutManager(context)
        setActivityTitle("Admin")
    }
    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

}