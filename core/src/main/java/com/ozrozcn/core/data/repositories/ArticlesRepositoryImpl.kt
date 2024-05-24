package com.ozrozcn.core.data.repositories

import android.util.Log
import com.ozrozcn.core.data.remote.apiservices.ArticleApiService
import com.ozrozcn.core.domain.mappers.ArticleMapper
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.core.domain.repositories.ArticleRepository
import com.ozrozcn.database.dao.ArticleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticleApiService,
    private val articleDao: ArticleDao,
    private val mapper: ArticleMapper
) : ArticleRepository {

    override val allArticles: Flow<List<Article>>
       get() = articleDao.getAllArticles().map { it.map(mapper::fromDao2Domain) }

    override val favoriteArticles: Flow<List<Article>>
        get() = articleDao.getFavoriteArticles().map { it.map(mapper::fromDao2Domain) }

    override suspend fun isFavorite(articleId: Int): Boolean =
        articleDao.getFavoriteStatus(articleId)

    override suspend fun getArticles()  {
        try {
            val response = api.getArticles()

            val remoteData = response.results

            articleDao.insertArticles(remoteData.map(mapper::fromDto2Entity))

            remoteData.map(mapper::fromDto2Domain)
        } catch (ex: Exception) {
            Log.i("OZER", "ex" + ex.localizedMessage)
        }
    }

    override suspend fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean) {
        articleDao.updateFavoriteStatus(articleId, isFavorite)
    }
}