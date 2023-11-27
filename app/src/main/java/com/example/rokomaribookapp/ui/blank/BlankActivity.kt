package com.example.rokomaribookapp.ui.blank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.ActivityBlankBinding

class BlankActivity : AppCompatActivity() {
    private lateinit var binding:ActivityBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            finish()
        }
    }
}