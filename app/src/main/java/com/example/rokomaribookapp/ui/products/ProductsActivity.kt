package com.example.rokomaribookapp.ui.products

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.ui.products.list.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: ProductListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val ans = intent.getStringExtra("categoryId")
        val bundle = Bundle()
        if (ans != null) {
            bundle.putLong("productId", ans.toLong())
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentproductcontainer) as NavHostFragment
        val navController = navHostFragment.navController
        navController
            .setGraph(R.navigation.products_graph, bundle)
    }
}
