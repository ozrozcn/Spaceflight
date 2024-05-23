package com.ozrozcn.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

//    @Binds
//    @Singleton
//    abstract fun bindGetProductsUseCase(
//        getProductsUseCaseImpl: GetArticleUseCaseImpl
//    ): GetArticlesUseCase
}