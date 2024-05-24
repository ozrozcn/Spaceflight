package com.ozrozcn.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.ozrozcn.core.BR

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : Fragment() {

    private var _binding: DB? = null
    val binding get() = _binding!!

    abstract var viewModel : VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DataBindingUtil.inflate<DB>(inflater, provideLayoutResId(), container, false)
            .apply { _binding = this }

        binding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @LayoutRes
    abstract fun provideLayoutResId(): Int
}