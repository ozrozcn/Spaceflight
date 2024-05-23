package com.ozrozcn.core.di

import com.google.gson.Gson
import com.ozrozcn.core.domain.mappers.ArticleMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideArticleMapper() = ArticleMapper()
}