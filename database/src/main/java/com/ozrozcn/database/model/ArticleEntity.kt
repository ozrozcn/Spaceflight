package com.ozrozcn.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val summary: String,
    val publishedAt: String,
    val url: String? = null,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)