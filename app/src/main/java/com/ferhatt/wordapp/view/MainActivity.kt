package com.ferhatt.wordapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.ferhatt.wordapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: WordFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}