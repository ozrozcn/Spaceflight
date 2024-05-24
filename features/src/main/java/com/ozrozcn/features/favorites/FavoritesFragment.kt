package com.ozrozcn.features.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ozrozcn.core.SharedViewModel
import com.ozrozcn.core.base.BaseFragment
import com.ozrozcn.core.common.Resource
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.features.R
import com.ozrozcn.features.common.ArticleListAdapter
import com.ozrozcn.features.databinding.FragmentFavoriteArticlesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<SharedViewModel, FragmentFavoriteArticlesBinding>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun provideLayoutResId(): Int = R.layout.fragment_favorite_articles

    override var viewModel: SharedViewModel
        get() = sharedViewModel
        set(value) {}

    private lateinit var articleAdapter: ArticleListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupArticleListView()
        setupObservers()

        sharedViewModel.getFavoriteArticles()
    }

    private fun setupArticleListView() {
        binding.rvArticles.apply {
            articleAdapter = ArticleListAdapter()
            adapter = articleAdapter
        }

        articleAdapter.onItemClick = { _, item ->
            navigateToDetail(item)
        }
    }

    private fun setupObservers() {
        sharedViewModel.favArticleListState()
            .onEach { state -> handleStateChange(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleStateChange(state: Resource<List<Article>>) {
        when (state) {
            is Resource.Success -> handleSuccess(state.data)
            is Resource.Error -> handleError(state.message)
            is Resource.Loading -> handleLoading()
            else -> Unit
        }
    }

    private fun handleLoading() {

    }

    private fun handleError(error: String?) {
    }

    private fun handleSuccess(list: List<Article>?) {
        articleAdapter.submitList(list)
    }

    private fun navigateToDetail(item: Article) {
        sharedViewModel.selectedItem = item
        findNavController().navigate(R.id.action_favoritesFragment_to_detailFragment)
    }
}