package com.example.rokomaribookapp.ui.account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.ActivityAccountBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = Firebase.auth
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewAccount) as NavHostFragment
        val navController = navHostFragment.navController
    }
}
