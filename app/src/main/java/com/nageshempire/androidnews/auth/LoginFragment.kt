package com.nageshempire.androidnews.auth

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentLoginBinding
import com.nageshempire.androidnews.onboarding.LanguagePreferenceActivity
import com.nageshempire.androidnews.util.view.BaseDataFragment
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseDataFragment<FragmentLoginBinding>(
    R.layout.fragment_login, null, null
) {

    override fun onLayoutInflated() {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignUpFragment)
        }
        binding.button2.setOnClickListener {
            invalidateCredentialsAndAuthenticate()
        }
    }

    private fun invalidateCredentialsAndAuthenticate() {
        val email = binding.editTextTextEmailAddress.text
        val password = binding.editTextTextPassword.text
        if (email.isNullOrEmpty() or password.isNullOrEmpty()) {
            requireContext().toast(getString(R.string.invalid_credentials))
        } else {
            authenticateWithEmail(email.toString(), password.toString())
        }
    }

    private fun authenticateWithEmail(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    requireContext().toast("Login Successful")
                    startActivity(Intent(requireActivity(), LanguagePreferenceActivity::class.java))
                    requireActivity().finish()
                }
            }.addOnFailureListener {
                if (it is FirebaseAuthInvalidUserException) {
                    requireContext().toast("No user found with email:$email")
                }
                it.localizedMessage?.let { it1 -> requireContext().toast(it1) }
            }
    }

}