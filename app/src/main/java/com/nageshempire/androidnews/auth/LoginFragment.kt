package com.nageshempire.androidnews.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentLoginBinding
import com.nageshempire.androidnews.onboarding.LanguagePreferenceActivity
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                }
            }.addOnFailureListener {
                if(it is FirebaseAuthInvalidUserException){
                    requireContext().toast("No user found with email:$email")
                }
                it.localizedMessage?.let { it1 -> requireContext().toast(it1) }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}