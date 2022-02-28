package com.example.deskbookingappllication.rcadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deskbookingappllication.databinding.CvDesksBinding
import com.example.deskbookingappllication.model.Desk

class RvDeskAdapter : RecyclerView.Adapter<RvDeskViewHolder>() {
    var deskList: List<Desk> = listOf()
    private lateinit var click: (desk: Desk) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvDeskViewHolder {
        val binding =
            CvDesksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RvDeskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvDeskViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = deskList[position]
            cvTvDesk.text = currentItem.label
            Glide.with(holder.binding.root).load(currentItem.map).into(cvIvDesk)
        }
        holder.binding.root.setOnClickListener {
            click { deskList[position] }
        }
    }

    override fun getItemCount() = deskList.size

    fun click(callback: (desk: Desk) -> Unit) {
        this.click = callback
    }

    fun swapData(data: List<Desk>) {
        this.deskList = data
        notifyDataSetChanged()
    }
}

class RvDeskViewHolder(val binding: CvDesksBinding) : RecyclerView.ViewHolder(binding.root)