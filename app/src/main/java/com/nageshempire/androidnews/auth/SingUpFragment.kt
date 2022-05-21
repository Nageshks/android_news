package com.nageshempire.androidnews.auth

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentSingUpBinding
import com.nageshempire.androidnews.onboarding.LanguagePreferenceActivity
import com.nageshempire.androidnews.util.view.BaseDataFragment
import com.nageshempire.androidnews.util.view.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingUpFragment : BaseDataFragment<FragmentSingUpBinding>(
    R.layout.fragment_sing_up, null, null
) {

    override fun onLayoutInflated() {
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

}