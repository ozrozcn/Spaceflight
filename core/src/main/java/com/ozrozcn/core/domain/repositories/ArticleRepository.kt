package com.ozrozcn.core.domain.repositories

import com.ozrozcn.core.data.remote.models.response.ArticleDto
import com.ozrozcn.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

     val allArticles : Flow<List<Article>>

    suspend fun getArticles(): Flow<List<Article>>
}