package com.example.deskbookingappllication.rcadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deskbookingappllication.databinding.CommentCvBinding

class RvCommentAdapter : RecyclerView.Adapter<RvCommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvCommentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RvCommentViewHolder, position: Int) {
        holder.binding.apply {

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class RvCommentViewHolder(val binding: CommentCvBinding) : RecyclerView.ViewHolder(binding.root) {

}
