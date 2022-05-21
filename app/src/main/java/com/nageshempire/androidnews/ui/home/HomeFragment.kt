package com.nageshempire.androidnews.ui.home

import androidx.fragment.app.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentHomeBinding
import com.nageshempire.androidnews.util.view.BaseDataFragment

class HomeFragment : BaseDataFragment<FragmentHomeBinding>(
    R.layout.fragment_home, null, null
) {
    private val viewModel by viewModels<HomeViewModel>()
    override fun onLayoutInflated() {

    }

    override fun setupObservables() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.text.text = it
        }
    }
}