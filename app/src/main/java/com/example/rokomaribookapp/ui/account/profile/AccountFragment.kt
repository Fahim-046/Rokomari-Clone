package com.example.rokomaribookapp.ui.account.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rokomaribookapp.databinding.FragmentAccountBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AccountFragment(
    private val onSuccess: () -> Unit
) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.dismissBtn.setOnClickListener {
            onSuccess()
            dismiss()
        }
    }

    private fun initViews() {
        Log.d("main", "I am in account")
    }

}
