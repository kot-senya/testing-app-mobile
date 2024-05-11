package com.example.testing_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing_app.Adapter.RecyclerMatchTheElemtAdapter
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentMatchTheElementBinding

class MatchTheElementFragment : Fragment() {
    private var _binding: FragmentMatchTheElementBinding? = null
    private val bind: FragmentMatchTheElementBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchTheElementBinding.inflate(inflater, container, false)

        loadElements()

        return bind.root
    }

    private fun loadElements(){
        val width = (Data.width.toDouble() * 0.48f).toInt()
        bind.groupName1.width = width
        bind.groupName2.width = width

        bind.groupName1.text = Data.questions[Data.currentQuestion-1].groups!![0].name
        bind.groupName2.text = Data.questions[Data.currentQuestion-1].groups!![1].name

        bind.rvElements.layoutManager = LinearLayoutManager(Data.context, LinearLayoutManager.VERTICAL,false)
        val adapter = RecyclerMatchTheElemtAdapter(Data.questions[Data.currentQuestion-1].groups!![0].elements_of_group,
            Data.questions[Data.currentQuestion-1].groups!![1].elements_of_group)
        bind.rvElements.adapter = adapter
    }
}