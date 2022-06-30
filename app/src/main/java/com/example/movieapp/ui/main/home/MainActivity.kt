package com.example.movieapp.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.MainActivityBinding
import com.example.movieapp.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    override fun getView(): View? {
        binding = MainActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(this, R.id.fragment)
        binding.btnNavigation.setupWithNavController(navController)

    //    window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }

}