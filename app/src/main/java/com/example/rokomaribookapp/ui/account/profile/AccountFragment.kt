package com.example.rokomaribookapp.ui.account.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AccountFragment(
    private val onSuccess: () -> Unit
) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
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
        binding.signOutLayout.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
            auth.signOut()
            googleSignInClient.signOut()
            onSuccess()
            dismiss()
        }
    }

    private fun initViews() {
        auth = Firebase.auth
        val user = auth.currentUser
        if (user != null) {
            binding.userNameTv.text = user.displayName
            if (user.phoneNumber?.isEmpty() == true) {
                binding.userNumberTv.text = "Not available"
            } else {
                binding.userNumberTv.text = user.phoneNumber
            }
            Log.d("img",user.photoUrl.toString())
            binding.accountImg.load(user.photoUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}
