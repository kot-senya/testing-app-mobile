package com.example.testing_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.databinding.PartItemNavigationBinding

class RecyclerNavigationAdapter(var elements: MutableList<Int>):RecyclerView.Adapter<NavigationViewHolder>() {
    private var listener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemNavigationBinding.inflate(inflate)
        return NavigationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: NavigationViewHolder, position: Int) {
        holder.bind.numElement.text = elements[position].toString()
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(holder, position)
            }
        }
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    interface ClickListener {
        fun onClick(holder: NavigationViewHolder, position: Int)
    }
}

class NavigationViewHolder(var bind: PartItemNavigationBinding): RecyclerView.ViewHolder(bind.root)