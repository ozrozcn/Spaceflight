package com.ozrozcn.core.data.repositories

import com.ozrozcn.core.data.remote.apiservices.ArticleApiService
import com.ozrozcn.core.domain.mappers.ArticleMapper
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.core.domain.repositories.ArticleRepository
import com.ozrozcn.database.dao.ArticleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticleApiService,
    private val articleDao: ArticleDao,
    private val mapper: ArticleMapper
) : ArticleRepository {

    override val allArticles: Flow<List<Article>> =
        articleDao.getAllArticles().map { it.map(mapper::fromDao2Domain) }

    override suspend fun getArticles(): Flow<List<Article>> = flow {
        try {
            val response = api.getArticles()

            val remoteData = response.results

            articleDao.insertArticles(remoteData.map(mapper::fromDto2Entity))

            remoteData.map(mapper::fromDto2Domain)
        } catch (ex: Exception) {

        }
    }
}