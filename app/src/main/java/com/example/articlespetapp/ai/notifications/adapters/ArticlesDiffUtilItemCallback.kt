package com.example.articlespetapp.ai.notifications.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.articlespetapp.model.Article

class ArticlesDiffUtilItemCallback: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}