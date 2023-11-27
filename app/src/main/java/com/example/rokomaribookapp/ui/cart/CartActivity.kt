package com.example.rokomaribookapp.ui.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rokomaribookapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView_order) as NavHostFragment
        navController = navHostFragment.navController
    }
}
