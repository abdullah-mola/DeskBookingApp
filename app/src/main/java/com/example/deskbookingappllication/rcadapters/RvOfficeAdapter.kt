package com.example.deskbookingappllication.rcadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deskbookingappllication.databinding.BookingPlanCvBinding
import com.example.deskbookingappllication.model.Office

class RvOfficeAdapter : RecyclerView.Adapter<RvViewHolder>() {
    var officeList: List<Office> = ArrayList()
    private lateinit var click: (office: Office) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val binding =
            BookingPlanCvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = officeList[position]
            cvTvOffice.text = currentItem.office_name
            Glide.with(holder.binding.root).load(currentItem.map).into(cvIvOffice)
        }
        holder.binding.root.setOnClickListener {
            click { officeList[position] }
        }
    }

    override fun getItemCount() = officeList.size

    fun click(callback: (office: Office) -> Unit) {
        this.click = callback
    }

    fun swapData(data: List<Office>) {
        this.officeList = data
        notifyDataSetChanged()
    }
}

class RvViewHolder(val binding: BookingPlanCvBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Office) = with(itemView) {
        // TODO: Bind the data with View
        setOnClickListener {
            // TODO: Handle on click
        }
    }
}