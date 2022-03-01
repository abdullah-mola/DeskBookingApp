package com.example.deskbookingappllication.rcadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deskbookingappllication.databinding.CvCommentsBinding
import com.example.deskbookingappllication.model.Comment

class RvCommentAdapter : RecyclerView.Adapter<RvCommentViewHolder>() {
    private var commentList: List<Comment> = listOf()
    private lateinit var click: (comment: Comment) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvCommentViewHolder {

        val binding =
            CvCommentsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return RvCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvCommentViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = commentList[position]
            tvComment.text = currentItem.comment

        }
        holder.binding.root.setOnClickListener {
            click { commentList[position] }
        }
    }

    override fun getItemCount() = commentList.size

    private fun click(callback: (comment: Comment) -> Unit) {
        this.click = callback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Comment>) {
        this.commentList = data
        notifyDataSetChanged()
    }
}

class RvCommentViewHolder(val binding: CvCommentsBinding) : RecyclerView.ViewHolder(binding.root)