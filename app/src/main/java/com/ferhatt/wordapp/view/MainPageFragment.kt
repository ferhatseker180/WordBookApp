package com.ferhatt.wordapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferhatt.wordapp.R
import com.ferhatt.wordapp.adapter.WordRecyclerAdapter
import com.ferhatt.wordapp.databinding.FragmentMainPageBinding
import com.ferhatt.wordapp.viewmodel.MainPageViewModel
import javax.inject.Inject


class MainPageFragment @Inject constructor(
    val wordRecyclerAdapter: WordRecyclerAdapter
) : Fragment(R.layout.fragment_main_page) {

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
            val selectedWord = wordRecyclerAdapter.words[layoutPosition]
            mainViewModel.deleteWord(selectedWord)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainPageViewModel::class.java)

        val tasarim = FragmentMainPageBinding.bind(view)
        mainPageBinding = tasarim

        subscribeToObservers()
        tasarim.recyclerViewMain.adapter = wordRecyclerAdapter
        tasarim.recyclerViewMain.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(tasarim.recyclerViewMain)

        tasarim.fab.setOnClickListener {
            findNavController().navigate(MainPageFragmentDirections.actionMainPageFragmentToWordDetailsFragment())
        }

    }

    private fun subscribeToObservers(){
        mainViewModel.wordList.observe(viewLifecycleOwner, Observer {
            wordRecyclerAdapter.words = it
        })
    }

}