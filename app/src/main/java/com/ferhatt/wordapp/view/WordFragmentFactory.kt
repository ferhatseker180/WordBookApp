package com.ferhatt.wordapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ferhatt.wordapp.adapter.ImageRecyclerAdapter
import com.ferhatt.wordapp.adapter.WordRecyclerAdapter
import javax.inject.Inject

class WordFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide : RequestManager,
    private val wordRecyclerAdapter: WordRecyclerAdapter) : FragmentFactory() {


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            WordDetailsFragment::class.java.name -> WordDetailsFragment(glide)
            MainPageFragment::class.java.name -> MainPageFragment(wordRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}