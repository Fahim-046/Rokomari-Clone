package com.example.rokomaribookapp.ui.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rokomaribookapp.databinding.ActivityElibraryBinding

class ElibraryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.topBar.setNavigationOnClickListener {
            finish()
        }
    }
}
