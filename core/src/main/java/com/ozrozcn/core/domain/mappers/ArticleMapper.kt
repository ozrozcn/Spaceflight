package com.ozrozcn.core.domain.mappers

import com.ozrozcn.core.data.remote.models.response.ArticleDto
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.database.model.ArticleEntity
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

class ArticleMapper {

    fun fromDto2Domain(article: ArticleDto): Article = with(article) {
        Article(
            id = id,
            title = title,
            summary = summary,
            publishDate = publishedAt.formatDate(),
            url = url,
            imageUrl = imageUrl
        )
    }

    fun fromDao2Domain(article: ArticleEntity): Article = with(article) {
        Article(
            id = id,
            title = title,
            summary = summary,
            publishDate = publishedAt.formatDate(),
            url = url,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
    }

    fun fromDto2Entity(article: ArticleDto): ArticleEntity = with(article) {
        ArticleEntity(
            id = id,
            title = title,
            summary = summary,
            publishedAt = publishedAt,
            url = url,
            imageUrl = imageUrl,
        )
    }

    private fun String.formatDate(): String {
        val zonedDateTime = ZonedDateTime.parse(this)

        val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .appendLiteral('.')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .appendLiteral('.')
            .appendValue(ChronoField.YEAR, 4)
            .toFormatter()

        return zonedDateTime.format(formatter)
    }
}