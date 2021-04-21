package com.example.spaceflightapi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import com.example.spaceflightapi.R
import com.example.spaceflightapi.adapters.BlogsRecyclerAdapter
import com.example.spaceflightapi.adapters.NewsRecyclerAdapter
import com.example.spaceflightapi.adapters.SpaceflightLoadStateAdapter
import com.example.spaceflightapi.databinding.NewsFragmentBinding
import com.example.spaceflightapi.di.AppComponent
import com.example.spaceflightapi.viewmodels.NewsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsFragment : DaggerFragment(R.layout.news_fragment){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : NewsViewModel by viewModels {
        viewModelFactory
    }

    private var _binding : NewsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = NewsFragmentBinding.bind(view)

        setArticlesRv()
        setBlogsRv()


    }

    private fun setArticlesRv(){

        val adapter = NewsRecyclerAdapter()

        binding.apply {
            blogArticlesRecyclerView.setHasFixedSize(true)
            blogArticlesRecyclerView.itemAnimator = null
            blogArticlesRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = SpaceflightLoadStateAdapter{adapter.retry()},
                footer = SpaceflightLoadStateAdapter{adapter.retry()}
            )

            retryButton.setOnClickListener {
                adapter.retry()
            }

        }
        viewModel.getArticles().observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle , it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {

                newsProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                blogArticlesRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                articlesTextView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1){
                    blogArticlesRecyclerView.isVisible = false
                    emptyTextView.isVisible = true
                }else{
                    emptyTextView.isVisible = false
                }

            }
        }

    }

    private fun setBlogsRv(){
        val adapter2 = BlogsRecyclerAdapter()

        binding.apply {
            newsRecyclerView.setHasFixedSize(true)
            newsRecyclerView.itemAnimator = null
            newsRecyclerView.adapter = adapter2.withLoadStateHeaderAndFooter(
                header = SpaceflightLoadStateAdapter{adapter2.retry()},
                footer = SpaceflightLoadStateAdapter{adapter2.retry()}
            )

            retryNewsButton.setOnClickListener {
                adapter2.retry()
            }

        }
        viewModel.getBlogs().observe(viewLifecycleOwner){
            adapter2.submitData(viewLifecycleOwner.lifecycle , it)
        }

        adapter2.addLoadStateListener { loadState ->
            binding.apply {

                newsProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                newsRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                topNewsTextView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryNewsButton.isVisible = loadState.source.refresh is LoadState.Error
                topeNewsTextView.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter2.itemCount < 1){
                    newsRecyclerView.isVisible = false
                    emptyTextView.isVisible = true
                }else{
                    emptyTextView.isVisible = false
                }

            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


