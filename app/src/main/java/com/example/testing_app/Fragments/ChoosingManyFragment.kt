package com.example.testing_app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing_app.Adapter.RecyclerChoosingManyAdapter
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentChoosingManyBinding


class ChoosingManyFragment : Fragment() {
    private var _binding: FragmentChoosingManyBinding? = null
    private val bind: FragmentChoosingManyBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoosingManyBinding.inflate(inflater, container, false)

        loadElements()

        return bind.root
    }

    private fun loadElements(){
        bind.rvElements.setHasFixedSize(true)
        bind.rvElements.layoutManager = LinearLayoutManager(Data.context, LinearLayoutManager.VERTICAL,false)
        val adapter = RecyclerChoosingManyAdapter(Data.questions[Data.currentQuestion-1].elements_of_choose)
        bind.rvElements.adapter = adapter
    }
}