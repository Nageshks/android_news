package com.nageshempire.androidnews.auth

import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentForgotPasswordBinding
import com.nageshempire.androidnews.util.view.BaseDataFragment
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseDataFragment<FragmentForgotPasswordBinding>(
    R.layout.fragment_forgot_password, null, null
) {

    override fun onLayoutInflated() {
        binding.button2.setOnClickListener {
            invalidateCredentialsAndAuthenticate()
        }
    }

    private fun invalidateCredentialsAndAuthenticate() {
        val email = binding.editTextTextEmailAddress.text
        if (!email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            sendForgetPasswordEmail(email.toString())
        } else {
            requireContext().toast(getString(R.string.invalid_credentials))
        }
    }

    private fun sendForgetPasswordEmail(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    requireContext().toast("Password Reset Link Send Successfully")
                }
            }.addOnFailureListener {
                it.localizedMessage?.let { it1 -> requireContext().toast(it1) }
            }
    }

}