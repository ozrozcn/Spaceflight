package com.ozrozcn.core.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val id: Int,
    val title: String,
    val summary: String,
    @SerializedName("published_at")
    val publishedAt: String,
    val url: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null
)