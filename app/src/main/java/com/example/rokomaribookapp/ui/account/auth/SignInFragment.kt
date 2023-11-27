package com.example.rokomaribookapp.ui.account.auth

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rokomaribookapp.R
import com.example.rokomaribookapp.databinding.FragmentSignInBinding
import com.example.rokomaribookapp.models.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel:SignInViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        binding.googleSignInBtn.setOnClickListener {
            signIn()
        }
        initListeners()
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account:GoogleSignInAccount? = task.result
            if(account!=null){
                updateUI(account)
            }
        }
        else{
            Toast.makeText(requireContext(),task.exception.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                viewModel.insertUser(
                    User(
                        auth.currentUser?.uid!!,
                        auth.currentUser?.displayName!!,
                        auth.currentUser?.phoneNumber,
                        auth.currentUser?.photoUrl.toString()
                    )
                )
                Toast.makeText(requireContext(), "Successfully signed in", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
            else{
                Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }

}
