package com.ozrozcn.core.domain.repositories

import com.ozrozcn.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    val allArticles: Flow<List<Article>>

    val favoriteArticles : Flow<List<Article>>

    suspend fun isFavorite(articleId: Int) : Boolean

    suspend fun getArticles()

    suspend fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean)
}