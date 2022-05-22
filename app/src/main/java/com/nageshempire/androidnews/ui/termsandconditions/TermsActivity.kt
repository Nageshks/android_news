package com.nageshempire.androidnews.ui.termsandconditions

import androidx.activity.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentTermsBinding
import com.nageshempire.androidnews.util.view.BaseDataActivity
import com.nageshempire.androidnews.util.view.setToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsActivity : BaseDataActivity<FragmentTermsBinding>(
    R.layout.fragment_terms
) {
    private val viewModel by viewModels<TermsViewModel>()
    override fun onLayoutInflated() {
        setToolbar(binding.toolbar)
        viewModel.data.observe(this) {
            binding.text.text = it
        }
    }
}
