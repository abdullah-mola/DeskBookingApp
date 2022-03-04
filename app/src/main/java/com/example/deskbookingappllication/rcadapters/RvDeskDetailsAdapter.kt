package com.example.deskbookingappllication.rcadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deskbookingappllication.databinding.CvDeskDetailsBinding
import com.example.deskbookingappllication.model.Desk

class RvDeskDetailsAdapter : RecyclerView.Adapter<DeskDetailsViewHolder>() {
    private var currentItem: Desk? = null
    private var deskEquipmentsList: List<Desk> = listOf()

    private lateinit var click: (deskEquipment: Desk) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeskDetailsViewHolder {

        val binding =
            CvDeskDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return DeskDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeskDetailsViewHolder, position: Int) {
        currentItem = deskEquipmentsList[position]
        holder.binding.apply {

            tvDeskDetails.text = currentItem!!.equipment.toString()

        }
        holder.binding.root.setOnClickListener {

            click(deskEquipmentsList[position])

        }
    }

    override fun getItemCount() = deskEquipmentsList.size

    fun click(callback: (deskEquioment: Desk) -> Unit) {
        this.click = callback
    }

    fun swapData(data: Desk) {
        this.deskEquipmentsList = listOf(data)
        notifyDataSetChanged()
    }
}


class DeskDetailsViewHolder(val binding: CvDeskDetailsBinding) :
    RecyclerView.ViewHolder(binding.root)