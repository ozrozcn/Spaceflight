package com.ozrozcn.core.domain.models

data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val publishDate: String,
    val url: String? = null,
    val imageUrl: String? = null,
    var isFavorite: Boolean = false
)