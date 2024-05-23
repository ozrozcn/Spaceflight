package com.ozrozcn.core.di

import com.ozrozcn.core.data.repositories.ArticlesRepositoryImpl
import com.ozrozcn.core.domain.repositories.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ArticlesRepositoryImpl
    ): ArticleRepository
}