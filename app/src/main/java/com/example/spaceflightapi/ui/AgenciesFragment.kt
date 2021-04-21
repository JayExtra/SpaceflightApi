package com.example.spaceflightapi.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import com.example.spaceflightapi.R
import com.example.spaceflightapi.adapters.AgenciesRecyclerAdapter
import com.example.spaceflightapi.adapters.SpaceflightLoadStateAdapter
import com.example.spaceflightapi.databinding.AgencyFragmentBinding
import com.example.spaceflightapi.viewmodels.AgenciesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.agency_fragment.*
import javax.inject.Inject

class AgenciesFragment : DaggerFragment(R.layout.agency_fragment) {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel : AgenciesViewModel by viewModels {
        viewModelFactory
    }
    private var _binding : AgencyFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = AgencyFragmentBinding.bind(view)

        setAgenciesRv()


    }

    fun setAgenciesRv(){


        //1.locate the adapter
        val adapter = AgenciesRecyclerAdapter()

        //2.prepare and attach the adapter to the recyclerview
        binding.apply {
            agenciesRecyclerView.setHasFixedSize(true)
            agenciesRecyclerView.itemAnimator = null
            agenciesRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = SpaceflightLoadStateAdapter{adapter.retry()},
                    footer = SpaceflightLoadStateAdapter{adapter.retry()}
            )
        }

        //3. prepare view model and get data
        viewModel.getAgencies().observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle , it)
        }

        //4.add load state listener to the adapter to handle various loading errors and also show the progressbar when loading new items
        adapter.addLoadStateListener {  loadState ->
            binding.apply {
                agencyFragmentProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                agenciesRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                agenciesRetryButton.isVisible = loadState.source.refresh is LoadState.Error
                agenciesErrorMessage.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1){
                    agenciesRecyclerView.isVisible = false
                    agenciesTextviewEmpty.isVisible = true
                }else{
                    agenciesTextviewEmpty.isVisible = false
                }

                agenciesRetryButton.setOnClickListener {
                    adapter.retry()
                }
            }
        }

    }
}