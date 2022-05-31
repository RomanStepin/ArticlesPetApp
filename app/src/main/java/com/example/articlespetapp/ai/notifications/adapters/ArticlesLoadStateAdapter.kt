package com.example.articlespetapp.ai.notifications.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.articlespetapp.databinding.ArticleLoadStateItemBinding

class ArticlesLoadStateAdapter: LoadStateAdapter<ArticlesLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ArticlesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ArticlesLoadStateViewHolder {
        val binding = ArticleLoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesLoadStateViewHolder(binding)
    }
}

class ArticlesLoadStateViewHolder(private val binding: ArticleLoadStateItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState)  {
        when(loadState) {
            is LoadState.Loading -> {
                binding.imageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, android.R.drawable.progress_indeterminate_horizontal))
            }
            is LoadState.NotLoading -> {
                binding.imageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, android.R.drawable.ic_menu_gallery))
            }
            is LoadState.Error -> {
                binding.imageView.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, android.R.drawable.ic_delete))
            }
        }
    }
}