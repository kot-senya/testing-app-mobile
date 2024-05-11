package com.example.testing_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.ElementOfChoose
import com.example.testing_app.databinding.PartItemChooseManyBinding

class RecyclerChoosingManyAdapter(var elements: MutableList<ElementOfChoose>?):RecyclerView.Adapter<ChoosingManyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosingManyViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemChooseManyBinding.inflate(inflate)
        return ChoosingManyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }

    override fun onBindViewHolder(holder: ChoosingManyViewHolder, position: Int) {
        holder.bind.itemElement.text = elements!![position].name
        holder.bind.itemElement.isChecked = elements!![position].userCorrectly
        holder.itemView.setOnClickListener {
            handleButtonChecks(position)
        }
    }

    private fun handleButtonChecks(position: Int){
        elements!![position].userCorrectly = !elements!![position].userCorrectly
        notifyItemChanged(position)
    }
}

class ChoosingManyViewHolder(var bind: PartItemChooseManyBinding): RecyclerView.ViewHolder(bind.root)