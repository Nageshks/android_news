package com.nageshempire.androidnews.ui.privacypolicy

import androidx.activity.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentPrivacyBinding
import com.nageshempire.androidnews.util.view.BaseDataActivity
import com.nageshempire.androidnews.util.view.setToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyActivity : BaseDataActivity<FragmentPrivacyBinding>(
    R.layout.fragment_privacy
) {
    private val viewModel by viewModels<PrivacyViewModel>()
    override fun onLayoutInflated() {
        setToolbar(binding.toolbar)
        viewModel.data.observe(this) {
            binding.text.text = it
        }
    }
}
