package com.example.articlespetapp.ai.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articlespetapp.App
import com.example.articlespetapp.ai.notifications.adapters.ArticlesAdapter
import com.example.articlespetapp.ai.notifications.adapters.ArticlesDiffUtilItemCallback
import com.example.articlespetapp.ai.notifications.adapters.ArticlesLoadStateAdapter
import com.example.articlespetapp.databinding.ArticlesFragmentBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ArticlesFragment : Fragment() {

    private lateinit var binding: ArticlesFragmentBinding
    @Inject
    lateinit var notificationsViewModelFactory: ArticlesViewModelFactory
    private lateinit var notificationsViewModel: ArticlesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArticlesFragmentBinding.inflate(inflater, container, false)
        App.notificationsComponent.injectNotificationsFragment(this)
        notificationsViewModel = ViewModelProvider(this, notificationsViewModelFactory)[ArticlesViewModel::class.java]
        return binding.root
   }

    @OptIn(ExperimentalPagingApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articlesRecyclerView = binding.recyclerView
        val articlesAdapter = ArticlesAdapter(ArticlesDiffUtilItemCallback())
        val articlesLoadStateAdapter = ArticlesLoadStateAdapter()
        articlesAdapter.withLoadStateHeaderAndFooter(articlesLoadStateAdapter, articlesLoadStateAdapter)
        articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        articlesRecyclerView.adapter = articlesAdapter
        lifecycleScope.launch {
            notificationsViewModel.flow.collectLatest {
                articlesAdapter.submitData(it)
                binding.root.isRefreshing = false
            }
        }

        binding.root.setOnRefreshListener {
            articlesAdapter.refresh()
        }
    }
}