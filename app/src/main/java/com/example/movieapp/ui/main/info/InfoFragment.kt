package com.example.movieapp.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.databinding.FragmentInfoBinding
import com.example.movieapp.ui.base.BaseFragment

class InfoFragment:BaseFragment() {

    private lateinit var binding:FragmentInfoBinding

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onViewDestroyed() {

    }
}