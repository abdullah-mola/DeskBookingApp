package com.example.deskbookingappllication.rcadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deskbookingappllication.databinding.CvDesksBinding
import com.example.deskbookingappllication.model.Desk


class RvDeskAdapter : RecyclerView.Adapter<RvDeskDetailsViewHolder>() {
    var currentItem: Desk? = null
    private var deskList: List<Desk> = listOf()
    private lateinit var click: (desk: Desk) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvDeskDetailsViewHolder {

        val binding =
            CvDesksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return RvDeskDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvDeskDetailsViewHolder, position: Int) {
        currentItem = deskList[position]
        holder.binding.apply {
            cvTvDesk.text = currentItem!!.label
            Glide.with(holder.binding.root).load(currentItem!!.map).into(cvIvDesk)
        }


        holder.binding.root.setOnClickListener {
            click(deskList[position])
        }
    }

    override fun getItemCount() = deskList.size

    fun click(callback: (desk: Desk) -> Unit) {
        this.click = callback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Desk>) {
        this.deskList = data
        notifyDataSetChanged()
    }
}

class RvDeskDetailsViewHolder(val binding: CvDesksBinding) : RecyclerView.ViewHolder(binding.root)