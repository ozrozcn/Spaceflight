package com.ozrozcn.features.article_list

import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import com.ozrozcn.core.common.FilterableListAdapter
import com.ozrozcn.core.domain.models.Article
import com.ozrozcn.features.R

class ArticleListAdapter : FilterableListAdapter<Article>(R.layout.item_article, ArticleDiffCallback()), Filterable{

    override fun shouldInclude(item: Article, query: String): Boolean {
        return item.summary.lowercase().contains(query) || item.title.lowercase().contains(query)
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}