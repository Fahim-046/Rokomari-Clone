package com.example.rokomaribookapp.ui.account.profile

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            MaterialAlertDialogBuilder(requireContext())
                .setMessage("Do you want to sign out?")
                .setNegativeButton("No") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("Yes") { dialog, which ->
                    // Respond to positive button press
                    lifecycleScope.launch {
                        binding.progressBar.isIndeterminate = true
                        delay(2000)
                        binding.progressBar.isIndeterminate = false
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
                .show()
        }
        binding.order.setOnClickListener {
            findNavController().navigate(R.id.orderPageFragment2)
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
            val imageLoader = ImageLoader.Builder(requireContext())
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            binding.accountImg.load(
                user.photoUrl,
                imageLoader = imageLoader
            ) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}
