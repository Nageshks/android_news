package com.nageshempire.androidnews.util.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener


abstract class BaseDataFragment<B : ViewDataBinding>(
    private val layout: Int,
    private val requests: Array<String>?,
    private val optionMenu: Int?
) :
    Fragment(), MenuProvider, FragmentResultListener, BaseFragment {
    private var _binding: B? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onLayoutInflated()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        requests?.forEach {
            parentFragmentManager.setFragmentResultListener(it, viewLifecycleOwner, this)
        }
        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        setupView()
        setupObservables()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        optionMenu?.let {
            menuInflater.inflate(optionMenu, menu)
            onMenuInflated(menu)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLayoutInflated() {

    }

    override fun setupView() {

    }

    override fun setupObservables() {

    }

    override fun onMenuInflated(menu: Menu) {

    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}