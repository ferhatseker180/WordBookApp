package com.ferhatt.wordapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ferhatt.wordapp.R
import com.ferhatt.wordapp.databinding.FragmentMainPageBinding
import com.ferhatt.wordapp.viewmodel.MainPageViewModel


class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private var mainPageBinding : FragmentMainPageBinding ?= null
    lateinit var mainViewModel : MainPageViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
           val layoutPosition = viewHolder.layoutPosition
          //  val selectedArt =
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasarim = FragmentMainPageBinding.bind(view)
        mainPageBinding = tasarim

    }

}