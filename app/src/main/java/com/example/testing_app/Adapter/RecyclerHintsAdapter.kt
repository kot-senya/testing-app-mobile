package com.example.testing_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing_app.DataClass.HintText
import com.example.testing_app.Helper.ActivityComponentLoad.openDialogCustom
import com.example.testing_app.R
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.DialogOptionUserChooseBinding
import com.example.testing_app.databinding.DialogOptionUserOkBinding
import com.example.testing_app.databinding.PartItemHintBinding
import kotlin.math.absoluteValue

class RecyclerHintsAdapter(var elements: MutableList<HintText>, var textView: TextView) :
    RecyclerView.Adapter<HintViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = PartItemHintBinding.inflate(inflate)
        return HintViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        textView.text = "Количество очков для подсказок: ${Data.appSetting.count_of_hints - Data.appSetting.user_count_hint}"
        val item = elements[position]
        if (item.inBasket) {
            if (item.text != null) {
                holder.bind.text.visibility = View.VISIBLE
                holder.bind.text.text = item.text.toString()
            } else holder.bind.text.visibility = View.GONE

            if (item.hint_images != null) {
                holder.bind.rvImages.visibility = View.VISIBLE
                holder.bind.rvImages.layoutManager =
                    LinearLayoutManager(Data.context, LinearLayoutManager.HORIZONTAL, false)
                holder.bind.rvImages.adapter =
                    RecyclerImageAdapter(Data.convertImageFromHintLine(item.hint_images!!))
            } else holder.bind.rvImages.visibility = View.GONE

            holder.bind.button.visibility = View.GONE

        } else {
            holder.bind.text.visibility = View.GONE
            holder.bind.rvImages.visibility = View.GONE

            holder.bind.cost.text = lineCost(item.cost)
            holder.bind.imageButton.setImageResource(R.drawable.icon_eye_closed)

            holder.bind.button.setOnClickListener {
                val countHint =
                    Data.appSetting.count_of_hints - (Data.appSetting.user_count_hint + item.cost)
                if (countHint >= 0 && !item.inBasket) {
                    val line: String =
                        "Вы действительно хотите купить подсказку за " + lineCost(item.cost) + "?\nПосле этого у вас останется " + lineCost(
                            countHint
                        )

                    val bind = DialogOptionUserChooseBinding.inflate(Data.layoutInflater)
                    bind.header.visibility = View.GONE
                    bind.text.text = line

                    val dialog = openDialogCustom(bind, Data.context!!)
                    Data.dialogUser = dialog

                    bind.buttonYes.setOnClickListener {
                        elements[position].inBasket = true
                        Data.appSetting.user_count_hint += item.cost
                        notifyItemChanged(position)
                        Data.dialogUser = null
                        dialog.dismiss()
                    }

                    bind.buttonNo.setOnClickListener {
                        Data.dialogUser = null
                        dialog.dismiss()
                    }

                } else {
                    val line: String =
                        "Вам не хватает " + outLineCost(countHint.absoluteValue) + " для открытия этой подсказки"

                    val bind = DialogOptionUserOkBinding.inflate(Data.layoutInflater)
                    bind.header.visibility = View.GONE
                    bind.text.text = line

                    val dialog = openDialogCustom(bind, Data.context!!)
                    Data.dialogUser = dialog

                    bind.buttonOk.setOnClickListener{
                        Data.dialogUser = null
                        dialog.dismiss()
                    }
                }
            }
        }
    }

    private fun lineCost(cost: Int): String {
        if(cost >=11 && cost <= 19)
            return "$cost очков"
        when (cost % 10) {
            1 -> return "$cost очко"
            2, 3, 4 -> return "$cost очка"
            else -> return "$cost очков"
        }
    }

    private fun outLineCost(cost: Int):String{
         when (cost % 10) {
            1 -> return "$cost очка"
            else -> return "$cost очков"
        }
    }
}

class HintViewHolder(var bind: PartItemHintBinding) : RecyclerView.ViewHolder(bind.root)