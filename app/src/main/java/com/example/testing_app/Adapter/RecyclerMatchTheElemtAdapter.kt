package com.example.testing_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.ElementOfGroup
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.PartItemMatchElementBinding

class RecyclerMatchTheElemtAdapter(
    var elements1: MutableList<ElementOfGroup>?,
    var elements2: MutableList<ElementOfGroup>?
) : RecyclerView.Adapter<MatchTheElementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchTheElementViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemMatchElementBinding.inflate(inflate)
        return MatchTheElementViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements1!!.size
    }

    override fun onBindViewHolder(holder: MatchTheElementViewHolder, position: Int) {
        holder.bind.textElement1.text = elements1!![position].name
        holder.bind.textElement2.text = elements2!![position].name

        holder.bind.textElement1.width = (Data.width.toDouble() * 0.35f).toInt()
        holder.bind.textElement2.width = (Data.width.toDouble() * 0.35f).toInt()

        holder.bind.numElement1.text = elements1!![position].user_ratio_numeri.toString()
        holder.bind.numElement2.text = elements2!![position].user_ratio_numeri.toString()

        holder.bind.textElement1.setOnClickListener { clickItem(position, 1) }
        holder.bind.textElement2.setOnClickListener { clickItem(position, 2) }
    }

    private fun clickItem(position: Int, groupNum: Int) {
        //проверка на остаточные значения
        nullableValue(groupNum)

        var firstlyMatches: Boolean = false
        when (groupNum) {
            1 -> firstlyMatches = elements1!![position].user_ratio_numeri == 0
            2 -> firstlyMatches = elements2!![position].user_ratio_numeri == 0
        }

        //если у элемента была пара,убираем у пары номер соединения
        if (!firstlyMatches)
            when (groupNum) {
                1 -> {
                    val elem =
                        elements2!!.find { it.user_ratio_numeri == elements1!![position].user_ratio_numeri }
                    if (elem != null) {
                        val indexMatch = elements2!!.indexOf(elem)
                        elements2!![indexMatch].user_ratio_numeri = 0
                        notifyItemChanged(indexMatch)
                    }
                }

                2 -> {
                    val elem =
                        elements1!!.find { it.user_ratio_numeri == elements2!![position].user_ratio_numeri }
                    if (elem != null) {
                        val indexMatch = elements1!!.indexOf(elem)
                        elements1!![indexMatch].user_ratio_numeri = 0
                        notifyItemChanged(indexMatch)
                    }
                }
            }

        //присвоение номера соединения
        when (groupNum) {
            1 -> {
                elements1!![position].user_ratio_numeri = numMatch(1)
                notifyItemChanged(position)
            }

            2 -> {
                elements2!![position].user_ratio_numeri = numMatch(2)
                notifyItemChanged(position)
            }
        }
    }

    private fun numMatch(groupNum: Int): Int {
        var value: Int = -1;

        when (groupNum) {
            1 -> {
                val elems = elements2!!.filter { it.user_ratio_numeri != 0 }
                for(i in 1.. elems.size)
                {
                    var elem = elements1!!.find { it.user_ratio_numeri == elems[i-1].user_ratio_numeri }
                    if(elem == null)
                        return elems[i-1].user_ratio_numeri
                }
            }

            2 -> {
                val elems = elements1!!.filter { it.user_ratio_numeri != 0 }
                for(i in 1.. elems.size)
                {
                    var elem = elements2!!.find { it.user_ratio_numeri == elems[i-1].user_ratio_numeri }
                    if(elem == null)
                        return elems[i-1].user_ratio_numeri
                }
            }
        }

        for (i in 1..elements1!!.size) {
            var elem1 = elements1!!.find { it.user_ratio_numeri == i }
            var elem2 = elements2!!.find { it.user_ratio_numeri == i }
            if (elem1 == null || elem2 == null)
                return i
        }
        return value;
    }

    private fun nullableValue(groupNum: Int) {

        var elems = elements2!!.filter { it.user_ratio_numeri != 0 }
        for (i in 1..elems.size) {
            val elem = elements1!!.find { it.user_ratio_numeri == elems[i - 1].user_ratio_numeri }
            if (elem == null && groupNum == 2) {
                val index = elements2!!.indexOf(elems[i-1])
                    elements2!![index].user_ratio_numeri = 0
                    notifyItemChanged(index)
            }
        }

        elems = elements1!!.filter { it.user_ratio_numeri != 0 }
        for (i in 1..elems.size) {
            val elem = elements2!!.find { it.user_ratio_numeri == elems[i - 1].user_ratio_numeri }
            if (elem == null && groupNum == 1) {
                val index = elements1!!.indexOf(elems[i-1])
                    elements1!![index].user_ratio_numeri = 0
                    notifyItemChanged(index)
            }
        }
    }
}

class MatchTheElementViewHolder(var bind: PartItemMatchElementBinding) :
    RecyclerView.ViewHolder(bind.root)