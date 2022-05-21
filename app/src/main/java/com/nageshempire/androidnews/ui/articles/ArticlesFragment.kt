package com.nageshempire.androidnews.ui.articles

import androidx.fragment.app.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentArticlesBinding
import com.nageshempire.androidnews.util.view.BaseDataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : BaseDataFragment<FragmentArticlesBinding>(
    R.layout.fragment_articles, null, null
) {
    private val viewModel by viewModels<ArticlesViewModel>()
    override fun onLayoutInflated() {
    }

    override fun setupObservables() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.text.text = it
        }
    }
}