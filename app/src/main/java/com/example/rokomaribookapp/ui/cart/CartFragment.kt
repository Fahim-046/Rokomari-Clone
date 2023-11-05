//package com.example.rokomaribookapp.ui.cart
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.rokomaribookapp.R
//import com.example.rokomaribookapp.adapters.CardAdapter
//import com.example.rokomaribookapp.databinding.FragmentCartBinding
//import com.example.rokomaribookapp.models.Books
//
//class CartFragment : Fragment(R.layout.fragment_cart) {
//    private lateinit var binding: FragmentCartBinding
//    private lateinit var cartList: MutableList<Books>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        cartList = mutableListOf(
//            Books(1, "Comics book", R.drawable.comics_1, null, "200"),
//            Books(2, "Comics book", R.drawable.comics2, null, "200"),
//            Books(3, "Comics book", R.drawable.comics_1, null, "200"),
//            Books(4, "Comics book", R.drawable.comics2, null, "200"),
//            Books(5, "Comics book", R.drawable.comics_1, null, "200"),
//            Books(6, "Comics book", R.drawable.comics2, null, "200")
//        )
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentCartBinding.bind(view)
//        if (cartList.isEmpty()) {
//            binding.cartRv.visibility = View.INVISIBLE
//            binding.totalAmountLayout.visibility = View.INVISIBLE
//            binding.confirmationCard.visibility = View.INVISIBLE
//            binding.noItemImg.visibility = View.VISIBLE
//            binding.noItemTv.visibility = View.VISIBLE
//        } else {
//            binding.cartRv.visibility = View.VISIBLE
//            binding.totalAmountLayout.visibility = View.VISIBLE
//            binding.noItemImg.visibility = View.GONE
//            binding.noItemTv.visibility = View.GONE
//        }
//        initViews()
//    }
//
//    private fun initViews() {
//        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
//        binding.cartRv.adapter = CardAdapter(cartList)
//        Log.d("main", "I am in cart")
//    }
//}
