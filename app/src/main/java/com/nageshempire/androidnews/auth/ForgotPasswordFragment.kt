package com.nageshempire.androidnews.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentForgotPasswordBinding
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}