package com.example.spaceflightapi.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.spaceflightapi.R
import com.example.spaceflightapi.databinding.NewsFragmentBinding
import com.example.spaceflightapi.databinding.SingleNewsCardBinding
import com.example.spaceflightapi.models.ArticlesResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewsRecyclerAdapter : PagingDataAdapter<ArticlesResponse , NewsRecyclerAdapter.NewsViewHolder>(ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = SingleNewsCardBinding.inflate(LayoutInflater.from(parent.context) , parent ,
        false)

        return NewsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }



    inner class NewsViewHolder(private val binding : SingleNewsCardBinding) :
            RecyclerView.ViewHolder(binding.root){


               // @RequiresApi(Build.VERSION_CODES.O)
               @RequiresApi(Build.VERSION_CODES.O)
               fun bind(article : ArticlesResponse){

                    binding.apply {
                        Glide.with(itemView)
                                .load(article.imageUrl)
                                .centerCrop()
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .error(R.drawable.ic_error)
                                .into(articleImageView)

                        newsTitleTextView.text= article.title

                        val date = article.publishedAt
                        //change to actual date
                        val simpleDateTimeFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
                        val final_date : Date = simpleDateTimeFormatter.parse(date)

                        dateTextView.text = final_date.toString()
                        publisherTextView.text = article.newsSite
                    }
                }


            }

    companion object {

        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<ArticlesResponse>(){
            override fun areItemsTheSame(oldItem: ArticlesResponse, newItem: ArticlesResponse): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticlesResponse, newItem: ArticlesResponse): Boolean =
                    oldItem == newItem



        }
    }




}