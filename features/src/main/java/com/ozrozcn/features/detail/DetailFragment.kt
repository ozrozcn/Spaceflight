package com.ozrozcn.features.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ozrozcn.core.SharedViewModel
import com.ozrozcn.core.base.BaseFragment
import com.ozrozcn.features.R
import com.ozrozcn.features.databinding.FragmentArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<SharedViewModel, FragmentArticleDetailBinding>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setArticleDetail()
    }
    override var viewModel: SharedViewModel
        get() = sharedViewModel
        set(value) {}

    override fun provideLayoutResId(): Int = R.layout.fragment_article_detail
}