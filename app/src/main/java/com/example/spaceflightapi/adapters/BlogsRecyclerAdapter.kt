package com.example.spaceflightapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.spaceflightapi.R
import com.example.spaceflightapi.databinding.SingleBlogCardBinding
import com.example.spaceflightapi.models.BlogResponse

class BlogsRecyclerAdapter : PagingDataAdapter<BlogResponse , BlogsRecyclerAdapter.BlogViewHolder>(BLOG_COMPARISON) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
      val binding = SingleBlogCardBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
       val currentItem = getItem(position)
        if(currentItem!=null){
            holder.bind(currentItem)
        }
    }


    inner class BlogViewHolder(private val binding:SingleBlogCardBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bind(blog: BlogResponse){

                    binding.apply{

                        Glide.with(itemView)
                                .load(blog.imageUrl)
                                .centerCrop()
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .error(R.drawable.ic_error)
                                .into(blogImageView)

                        textViewBlogTitle.text=blog.title

                    }

                }


    }

    companion object {
        private val BLOG_COMPARISON = object : DiffUtil.ItemCallback<BlogResponse>(){
            override fun areItemsTheSame(oldItem: BlogResponse, newItem: BlogResponse): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: BlogResponse, newItem: BlogResponse): Boolean =
               oldItem == newItem

        }
    }


}