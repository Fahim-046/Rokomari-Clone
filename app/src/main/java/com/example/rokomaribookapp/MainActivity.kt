package com.example.rokomaribookapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.rokomaribookapp.ui.account.AccountActivity
import com.example.rokomaribookapp.ui.account.profile.AccountFragment
import com.example.rokomaribookapp.ui.cart.CartActivity
import com.example.rokomaribookapp.ui.library.ElibraryActivity
import com.example.rokomaribookapp.ui.menu.MenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var cartSheet: BottomSheetDialogFragment
    private lateinit var menuSheet: BottomSheetDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navView = findViewById(R.id.bottom_navigation)
        // navView.setupWithNavController(navController)
        // findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.cartFragment -> {
                    val intent = Intent(this@MainActivity, CartActivity::class.java)
                    startActivity(intent)
                }

                R.id.accountFragment -> {
                    val user = Firebase.auth.currentUser
                    if (user != null) {
                        cartSheet = AccountFragment {
                            navView.selectedItemId = R.id.homeFragment
                        }
                        cartSheet.isCancelable = false
                        cartSheet.show(supportFragmentManager, "account sheet")
                    } else {
                        val intent = Intent(this@MainActivity, AccountActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.libraryFragment -> {
                    val user = Firebase.auth.currentUser
                    // findNavController(R.id.fragmentContainerView).navigate(R.id.libraryFragment)
                    if (user != null) {
                        val intent = Intent(this@MainActivity, ElibraryActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@MainActivity, AccountActivity::class.java)
                        startActivity(intent)
                    }
                }

                R.id.menuFragment -> {
                    menuSheet = MenuFragment {
                        navView.selectedItemId = R.id.homeFragment
                    }
                    menuSheet.isCancelable = false
                    menuSheet.show(supportFragmentManager, "menu sheet")
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if (navView.selectedItemId != R.id.homeFragment) {
            supportFragmentManager.popBackStack()
            navView.selectedItemId = R.id.homeFragment
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        navView.selectedItemId = R.id.homeFragment
    }
}
