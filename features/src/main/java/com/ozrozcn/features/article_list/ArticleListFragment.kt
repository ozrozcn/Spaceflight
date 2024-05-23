package com.ozrozcn.features.article_list

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ozrozcn.core.SharedViewModel
import com.ozrozcn.core.common.Resource
import com.ozrozcn.core.common.extensions.observeInLifecycle
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.features.R
import com.ozrozcn.features.databinding.FragmentArticleListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ArticleListFragment : Fragment(R.layout.fragment_article_list) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!

    private lateinit var articleAdapter: ArticleListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleListBinding.bind(view)

        setupLayout()
        setupArticleListView()
        setupObservers()
    }

    private fun setupLayout() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                articleAdapter.filter.filter(newText)
                return true
            }
        })
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

    private fun navigateToDetail(item: Article) {
        sharedViewModel.setSelectedItem = item
        findNavController().navigate(R.id.action_articleListFragment_to_detailFragment)
    }

    private fun setupObservers() {
        sharedViewModel.articleListState()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}