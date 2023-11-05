package com.example.rokomaribookapp.ui.menu

import android.os.Bundle
import android.view.View
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuFragment(
    private val onSuccess: () -> Unit
) : BottomSheetDialogFragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.dismissBtn.setOnClickListener {
            onSuccess()
            dismiss()
        }
    }
}
