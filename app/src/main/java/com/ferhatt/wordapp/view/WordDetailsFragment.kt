package com.ferhatt.wordapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.ferhatt.wordapp.R
import com.ferhatt.wordapp.databinding.FragmentWordDetailsBinding
import com.ferhatt.wordapp.util.Status
import com.ferhatt.wordapp.viewmodel.MainPageViewModel
import javax.inject.Inject


class WordDetailsFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_word_details) {

    lateinit var viewModel : MainPageViewModel
    private var detailBinding : FragmentWordDetailsBinding ?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainPageViewModel::class.java)

        val tasarim = FragmentWordDetailsBinding.bind(view)
        detailBinding = tasarim

        subscribeToObservers()

        tasarim.wordImageView.setOnClickListener {
            findNavController().navigate(
                WordDetailsFragmentDirections.actionWordDetailsFragmentToImageApiFragment()
            )
        }

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        tasarim.saveButton.setOnClickListener {
            viewModel.makeWord(tasarim.wordEditText.text.toString(),
                tasarim.wordDescriptionText.text.toString())
        }

    }

    private fun subscribeToObservers() {
        viewModel.selectedUrl.observe(viewLifecycleOwner, Observer { url ->
            println(url)
            detailBinding?.let {
                glide.load(url).into(it.wordImageView)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(),"Success",Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                    viewModel.resetInsertMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })
    }


}