package com.example.testing_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.testing_app.Adapter.RecyclerArrangamentAdapter
import com.example.testing_app.Helper.MyItemTouchHelperCallBack
import com.example.testing_app.Helper.onStartDragListener
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.FragmentArrangamentBinding

class ArrangamentFragment : Fragment() {
    private var _binding: FragmentArrangamentBinding? = null
    private val bind: FragmentArrangamentBinding get() = _binding!!

    var itemTouchHelper:ItemTouchHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArrangamentBinding.inflate(inflater, container, false)

        loadElements()

        return bind.root
    }

    private fun loadElements(){
        bind.rvElements.setHasFixedSize(true)
        bind.rvElements.layoutManager = GridLayoutManager(Data.context,1 )
        val adapter = RecyclerArrangamentAdapter(Data.context!!, Data.questions[Data.currentQuestion-1].elements_of_arrangment,
            object : onStartDragListener{
                override fun onStartDrag(viewHolder: ViewHolder) {
                    itemTouchHelper!!.startDrag(viewHolder)
                }
            })
        bind.rvElements.adapter = adapter
        val callback = MyItemTouchHelperCallBack(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper!!.attachToRecyclerView(bind.rvElements)
    }
}