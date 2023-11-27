//package com.example.rokomaribookapp.ui.account.auth
//
//import android.app.Activity.RESULT_OK
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.activity.result.IntentSenderRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import com.example.rokomaribookapp.R
//import com.example.rokomaribookapp.databinding.FragmentSignInBinding
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.auth.auth
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//@AndroidEntryPoint
//class AnotherFragment : Fragment(R.layout.fragment_sign_in) {
//    private lateinit var binding: FragmentSignInBinding
//    private val viewModel: SignInViewModel by viewModels()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentSignInBinding.bind(view)
//        binding.googleSignInBtn.setOnClickListener {
//            signIn()
//        }
//    }
//
//    private fun signIn() {
//        lifecycleScope.launch {
//            viewModel.oneTapSignInWithGoogle()?.let { result ->
//                val intentSenderRequest = IntentSenderRequest
//                    .Builder(result.pendingIntent.intentSender)
//                    .build()
//                signInLauncher.launch(intentSenderRequest)
//            }
//        }
//    }
//
//    val signInLauncher = registerForActivityResult(
//        ActivityResultContracts.StartIntentSenderForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            try {
//                val credential = viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
//                val googleIdToken = credential.googleIdToken
//                val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
//
//                viewModel.firebaseSignInWithGoogle(credential, googleCredentials)
//            } catch (e: ApiException) {
//                Toast.makeText(requireContext(), "Jani na ", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
