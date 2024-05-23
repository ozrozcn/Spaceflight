package com.ozrozcn.core.data.remote.apiservices

import com.ozrozcn.core.data.remote.models.response.ArticleListResponseDto
import retrofit2.http.GET

interface ArticleApiService {

    @GET(value = "articles")
    suspend fun getArticles() : ArticleListResponseDto
}