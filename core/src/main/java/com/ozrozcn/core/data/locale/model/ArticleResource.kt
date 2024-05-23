package com.ozrozcn.core.data.locale.model

data class ArticleResource(
    val id: Int,
    val title: String,
    val summary: String,
    val publishedAt: String,
    val url: String? = null,
    val imageUrl: String? = null,
    val isFavorite: Boolean? = false
)