package com.example.testing_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing_app.Adapter.RecyclerMatchTheValueAdapter
import com.example.testing_app.DataClass.matchTheValue
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentMatchTheValueBinding

class MatchTheValueFragment : Fragment() {
    private var _binding: FragmentMatchTheValueBinding? = null
    private val bind: FragmentMatchTheValueBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchTheValueBinding.inflate(inflater, container, false)

        loadData()
        loadElements()

        return bind.root
    }

    private fun loadData() {
        if (Data.questions[Data.currentQuestion - 1].matchingValues.size == 0) {
            val countMatches =
                Data.questions[Data.currentQuestion - 1].elements_of_equality!!.size / 2
            for (i in 1..countMatches) {
                Data.questions[Data.currentQuestion - 1].matchingValues.add(
                    matchTheValue(
                        null,
                        null
                    )
                )
            }
        }

        if(Data.questions[Data.currentQuestion - 1].valuesForMatch.size == 0){
            val list:MutableList<String> = mutableListOf()
            list.add("--")
            for(elem in Data.questions[Data.currentQuestion - 1].elements_of_equality!!){
                list.add(elem.name)
            }

            Data.questions[Data.currentQuestion - 1].valuesForMatch= list
        }
    }

    private fun loadElements() {
        bind.rvElements.layoutManager =
            LinearLayoutManager(Data.context, LinearLayoutManager.VERTICAL, false)
        val adapter = RecyclerMatchTheValueAdapter(
            Data.questions[Data.currentQuestion - 1].matchingValues,
            Data.questions[Data.currentQuestion - 1].valuesForMatch,
            Data.questions[Data.currentQuestion - 1].elements_of_equality
        )
        bind.rvElements.adapter = adapter
    }
}