package com.nageshempire.androidnews.ui.mycity

import androidx.fragment.app.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentMyCityBinding
import com.nageshempire.androidnews.util.view.BaseDataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCityFragment : BaseDataFragment<FragmentMyCityBinding>(
    R.layout.fragment_my_city, null, null
) {
    private val viewModel by viewModels<MyCityViewModel>()
    override fun onLayoutInflated() {

    }

    override fun setupObservables() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.text.text = it
        }
    }
}