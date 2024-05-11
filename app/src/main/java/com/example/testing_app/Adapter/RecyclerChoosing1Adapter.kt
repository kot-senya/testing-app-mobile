package com.example.testing_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.ElementOfChoose
import com.example.testing_app.databinding.PartItemChoose1Binding

class RecyclerChoosing1Adapter(var elements: MutableList<ElementOfChoose>?):RecyclerView.Adapter<Choosing1ViewHolder>() {
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Choosing1ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemChoose1Binding.inflate(inflate)
        return Choosing1ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }

    override fun onBindViewHolder(holder: Choosing1ViewHolder, position: Int) {
        holder.bind.itemElement.text = elements!![position].name
        holder.bind.itemElement.isChecked = elements!![position].userCorrectly
        holder.itemView.setOnClickListener {
            handleRadioButtonChecks(position)
        }
    }

    private fun handleRadioButtonChecks(position: Int){
        if(lastPosition > -1) {
            elements!![lastPosition].userCorrectly = false
            notifyItemChanged(lastPosition)
        }
        elements!![position].userCorrectly = true
        lastPosition = position
        notifyItemChanged(position)
    }
}

class Choosing1ViewHolder(var bind: PartItemChoose1Binding): RecyclerView.ViewHolder(bind.root)