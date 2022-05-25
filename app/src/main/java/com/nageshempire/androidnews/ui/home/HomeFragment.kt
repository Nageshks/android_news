package com.nageshempire.androidnews.ui.home

import androidx.fragment.app.viewModels
import com.nageshempire.androidnews.R
import com.nageshempire.androidnews.databinding.FragmentHomeBinding
import com.nageshempire.androidnews.homeVideoItem
import com.nageshempire.androidnews.util.view.BaseDataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseDataFragment<FragmentHomeBinding>(
    R.layout.fragment_home, null, null
) {
    private val viewModel by viewModels<HomeViewModel>()
    override fun onLayoutInflated() {

    }

    override fun setupObservables() {
        viewModel.breakingNews.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }

    private fun updateList(it: List<HomeNewsItem>) {
        binding.list.withModels {
            it.forEach { news ->
                homeVideoItem {
                    id(news.newsTitle)
                    data(news)
                }
            }
        }
    }
}