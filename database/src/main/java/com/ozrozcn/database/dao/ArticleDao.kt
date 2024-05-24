package com.ozrozcn.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozrozcn.database.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query(value = "SELECT * FROM articles")
    fun getAllArticles() : Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query(value = "SELECT * FROM articles WHERE isFavorite==1")
    fun getFavoriteArticles(): Flow<List<ArticleEntity>>

    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE id = :articleId")
    suspend fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean)

    @Query("SELECT isFavorite FROM articles WHERE id = :articleId")
    suspend fun getFavoriteStatus(articleId: Int): Boolean
}