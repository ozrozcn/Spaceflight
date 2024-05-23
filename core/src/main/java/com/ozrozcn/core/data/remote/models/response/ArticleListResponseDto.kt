package com.ozrozcn.core.data.remote.models.response


data class ArticleListResponseDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ArticleDto>
)
