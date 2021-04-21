package com.example.spaceflightapi.adapters

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.spaceflightapi.R
import com.example.spaceflightapi.databinding.AgenciesSingleItemBinding
import com.example.spaceflightapi.models.AgenciesList
import com.example.spaceflightapi.models.AgenciesResponse
import com.example.spaceflightapi.models.BlogResponse

class AgenciesRecyclerAdapter : PagingDataAdapter<AgenciesList , AgenciesRecyclerAdapter.AgenciesViewHolder>(AGENCY_COMPARISON) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgenciesViewHolder {
       val binding = AgenciesSingleItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return AgenciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgenciesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem!=null){
            holder.bind(currentItem)
        }
    }
    inner class AgenciesViewHolder(private val binding : AgenciesSingleItemBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bind(agency : AgenciesList){

                    binding.apply {

                        if(agency.image_url != null){

                            Glide.with(itemView)
                                    .load(agency.image_url)
                                    .transition(DrawableTransitionOptions.withCrossFade())
                                    .centerCrop()
                                    .error(R.drawable.ic_error)
                                    .into(profileImage)
                        }else{
                            Glide.with(itemView)
                                    .load(R.drawable.ic_image)
                                    .transition(DrawableTransitionOptions.withCrossFade())
                                    .centerCrop()
                                    .error(R.drawable.ic_error)
                                    .into(profileImage)
                        }


                        agencyNameTextView.text = agency.name
                        countryTextView.text = agency.country_code
                        typeTextView.text = agency.type

                        //executePendingBindings()
                    }
                }

            }

    companion object {
        private val AGENCY_COMPARISON = object : DiffUtil.ItemCallback<AgenciesList>(){
            override fun areItemsTheSame(oldItem: AgenciesList, newItem: AgenciesList): Boolean =
                    oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: AgenciesList, newItem: AgenciesList): Boolean =
                    oldItem == newItem

        }
    }

}