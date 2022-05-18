package com.nageshempire.androidnews.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentSingUpBinding
import com.nageshempire.androidnews.onboarding.LanguagePreferenceActivity
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SingUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_SignUpFragment_to_ForgotPasswordFragment)
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_SignUpFragment_to_LoginFragment)
        }
        binding.button2.setOnClickListener {
            invalidateCredentialsAndAuthenticate()
        }

    }

    private fun invalidateCredentialsAndAuthenticate() {
        val name = binding.editTextTextPersonName.text
        val email = binding.editTextTextEmailAddress.text
        val password = binding.editTextTextPassword.text
        val password2 = binding.editTextTextPassword2.text
        if (name.isNullOrEmpty()) {
            requireContext().toast(getString(R.string.name_invalid))
            return
        }
        if (email.isNullOrEmpty() or !password.contentEquals(password2)) {
            requireContext().toast(getString(R.string.invalid_credentials))
        } else {
            authenticateWithEmail(email.toString(), password.toString())
        }
    }

    private fun authenticateWithEmail(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    requireContext().toast("Signup Successful")
                    startActivity(Intent(requireActivity(), LanguagePreferenceActivity::class.java))
                }
            }.addOnFailureListener {
                if(it is FirebaseAuthUserCollisionException){
                    requireContext().toast("Already have an account login please to continue")
                    findNavController().navigate(R.id.action_SignUpFragment_to_LoginFragment)
                }else{
                    it.localizedMessage?.let { it1 -> requireContext().toast(it1) }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}