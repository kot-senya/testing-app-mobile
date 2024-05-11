package com.example.testing_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing_app.Adapter.RecyclerChoosing1Adapter
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentChoosingOneBinding

class ChoosingOneFragment : Fragment() {
    private var _binding: FragmentChoosingOneBinding? = null
    private val bind: FragmentChoosingOneBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoosingOneBinding.inflate(inflater, container, false)

        loadElements()

        return bind.root
    }

    private fun loadElements(){
        bind.rvElements.setHasFixedSize(true)
        bind.rvElements.layoutManager = LinearLayoutManager(Data.context, LinearLayoutManager.VERTICAL,false)
        val adapter = RecyclerChoosing1Adapter(Data.questions[Data.currentQuestion-1].elements_of_choose)
        bind.rvElements.adapter = adapter
    }
}