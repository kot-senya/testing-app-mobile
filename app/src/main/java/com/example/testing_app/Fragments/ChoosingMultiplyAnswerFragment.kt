package com.example.testing_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testing_app.Adapter.RecyclerChoosingMultiplyAdapter
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentChoosingMultiplyAnswerBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class ChoosingMultiplyAnswerFragment : Fragment() {
    private var _binding: FragmentChoosingMultiplyAnswerBinding? = null
    private val bind: FragmentChoosingMultiplyAnswerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoosingMultiplyAnswerBinding.inflate(inflater, container, false)

        loadElements()

        return bind.root
    }

    private fun loadElements(){
        val layoutManager = FlexboxLayoutManager(Data.context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.flexWrap = FlexWrap.WRAP
        bind.rvElements.layoutManager = layoutManager
        val adapter = RecyclerChoosingMultiplyAdapter(Data.questions[Data.currentQuestion-1].elements_of_putting)
        bind.rvElements.adapter = adapter
    }
}