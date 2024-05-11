package com.example.testing_app.Adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.ElementOfEquality
import com.example.testing_app.DataClass.matchTheValue
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.PartItemMatchValueBinding

class RecyclerMatchTheValueAdapter(
    var elements: MutableList<matchTheValue>,
    var values: MutableList<String>,
    val originalData: MutableList<ElementOfEquality>?
) : RecyclerView.Adapter<MatchTheValueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchTheValueViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemMatchValueBinding.inflate(inflate)
        return MatchTheValueViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: MatchTheValueViewHolder, position: Int) {
        val adapter = ArrayAdapter(Data.context!!, R.layout.simple_spinner_dropdown_item, values)

        holder.bind.itemElem1.adapter = adapter
        holder.bind.itemElem2.adapter = adapter

        var elem1 = 0
        if (elements[position].elem1 != null) elem1 = values.indexOf(elements[position].elem1!!.name)
        var elem2 = 0
        if (elements[position].elem2 != null) elem2 = values.indexOf(elements[position].elem2!!.name)

        holder.bind.itemElem1.setSelection(elem1)
        holder.bind.itemElem2.setSelection(elem2)

        val pos = position //для обращения внутри OnItemSelectedListener

        holder.bind.itemElem1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    elements[pos].elem1 = null
                } else {
                    val elem = originalData!![position - 1]
                    elements[pos].elem1 = elem
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        holder.bind.itemElem2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    elements[pos].elem2 = null
                } else {
                    val elem = originalData!![position - 1]
                    elements[pos].elem2 = elem
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        holder.bind.itemElem1.layoutParams.width = (Data.width.toDouble() * 0.38f).toInt()
        holder.bind.itemElem2.layoutParams.width = (Data.width.toDouble() * 0.38f).toInt()
    }
}

class MatchTheValueViewHolder(var bind: PartItemMatchValueBinding) :
    RecyclerView.ViewHolder(bind.root)