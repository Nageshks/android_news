package com.nageshempire.androidnews.ui.live

import androidx.fragment.app.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentLiveBinding
import com.nageshempire.androidnews.util.view.BaseDataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveFragment : BaseDataFragment<FragmentLiveBinding>(
    R.layout.fragment_live, null, null
) {
    private val viewModel by viewModels<LiveViewModel>()
    override fun onLayoutInflated() {

    }

    override fun setupObservables() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.text.text = it
        }
    }
}