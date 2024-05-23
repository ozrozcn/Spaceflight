package com.ozrozcn.database.di

import com.ozrozcn.database.AppDatabase
import com.ozrozcn.database.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun providesArticleDao(database : AppDatabase) : ArticleDao = database.articleDao()
}