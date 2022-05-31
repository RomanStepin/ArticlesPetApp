package com.example.articlespetapp.ai.notifications.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.articlespetapp.R
import com.example.articlespetapp.databinding.ArticleItemLayoutBinding
import com.example.articlespetapp.getHighlightedString
import com.example.articlespetapp.model.Article

class ArticlesAdapter(itemCallback: DiffUtil.ItemCallback<Article>): PagingDataAdapter<Article, ArticlesViewHolder>(itemCallback) {
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding = ArticleItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticlesViewHolder(binding)
    }
}


class ArticlesViewHolder(private val binding: ArticleItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article?) {
        binding.articleAuthor.text = article?.author?.getHighlightedString(ContextCompat.getColor(binding.root.context, R.color.gray))
        binding.articleTitle.text = article?.title?.getHighlightedString(ContextCompat.getColor(binding.root.context, R.color.gray))
        Glide.with(binding.root)
            .load(article?.urlToImage)
            .placeholder(android.R.drawable.progress_horizontal)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.articleImage)
    }
}