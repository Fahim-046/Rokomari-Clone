package com.example.rokomaribookapp.ui.author

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rokomaribookapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView_author) as NavHostFragment
        navController = navHostFragment.navController
    }
}
