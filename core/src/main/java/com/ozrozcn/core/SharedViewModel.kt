package com.ozrozcn.core

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozrozcn.core.domain.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ozrozcn.core.common.Resource
import com.ozrozcn.core.domain.repositories.ArticleRepository

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    lateinit var selectedItem: Article

    private val _articleListState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading())
    fun articleListState(): StateFlow<Resource<List<Article>>> = _articleListState.asStateFlow()

    private val _favArticleListState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading())
    fun favArticleListState(): StateFlow<Resource<List<Article>>> = _favArticleListState.asStateFlow()

    val articleDetailOF = ObservableField<Article>()

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            articleRepository.getArticles()
            articleRepository.allArticles.collect { result ->
                result.let {
                    _articleListState.value = Resource.Success(data = it)
                }
            }
        }
    }

    fun getFavoriteArticles(){
        viewModelScope.launch {
            articleRepository.favoriteArticles.collect { result ->
                result.let {
                    _favArticleListState.value = Resource.Success(data = it)
                }
            }
        }
    }

    fun setArticleDetail() {
        articleDetailOF.set(selectedItem)
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            selectedItem.isFavorite = !selectedItem.isFavorite
            articleRepository.updateFavoriteStatus(selectedItem.id, selectedItem.isFavorite)
        }
    }
}