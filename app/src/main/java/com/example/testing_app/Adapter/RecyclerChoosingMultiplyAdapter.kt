package com.example.testing_app.Adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.TextOfPutting
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.PartItemChooseMultiplayBinding

class RecyclerChoosingMultiplyAdapter(var elements: MutableList<TextOfPutting>?):RecyclerView.Adapter<ChoosingMultiplyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosingMultiplyViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemChooseMultiplayBinding.inflate(inflate)
        return ChoosingMultiplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements!!.size
    }

    override fun onBindViewHolder(holder: ChoosingMultiplyViewHolder, position: Int) {
        holder.bind.elemText.text = elements!![position].name

        var values: MutableList<String> = mutableListOf()
        values.add("--")
        for(elem in elements!![position].elements!!)
            values.add(elem.name)

        val adapter = ArrayAdapter(Data.context!!, R.layout.simple_spinner_dropdown_item, values)

        holder.bind.elemChoosing.adapter = adapter

        var selectedElem = 0
        if (elements!![position].elem != null) selectedElem = values.indexOf(elements!![position].elem!!.name)

        holder.bind.elemChoosing.setSelection(selectedElem)

        val pos = position //для обращения внутри OnItemSelectedListener

        holder.bind.elemChoosing.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    elements!![pos].elem = null
                } else {
                    val elem = elements!![pos].elements!![position - 1]
                    elements!![pos].elem = elem
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}

class ChoosingMultiplyViewHolder(var bind: PartItemChooseMultiplayBinding): RecyclerView.ViewHolder(bind.root)