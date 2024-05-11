package com.example.testing_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.ElementOfArrangement
import com.example.testing_app.Helper.ItemTouchHelperAdapter
import com.example.testing_app.Helper.onStartDragListener
import com.example.testing_app.databinding.PartItemArrangamentBinding
import java.util.Collections

class RecyclerArrangamentAdapter(var context:Context, var elements: MutableList<ElementOfArrangement>?,var listener: onStartDragListener):RecyclerView.Adapter<ArrangamentViewHolder>(), ItemTouchHelperAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrangamentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemArrangamentBinding.inflate(inflate)
        return ArrangamentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }

    override fun onBindViewHolder(holder: ArrangamentViewHolder, position: Int) {
        holder.bind.textElement.text = elements!![position].name
        holder.bind.itemArrangament.setOnClickListener {
            listener.onStartDrag(holder)
            false
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int):Boolean {
        Collections.swap(elements!!, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
    }


}

class ArrangamentViewHolder(var bind: PartItemArrangamentBinding):RecyclerView.ViewHolder(bind.root)