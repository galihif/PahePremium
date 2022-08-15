package com.giftech.filmku.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giftech.filmku.R
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.databinding.ItemMovieBinding
import com.giftech.filmku.utils.AppUtils
import com.giftech.filmku.utils.AppUtils.createGenreChip
import com.giftech.filmku.utils.AppUtils.loadMovieImage

class PopularAdapter: ListAdapter<Movie, PopularAdapter.PopularViewHolder>(DIFF_CALLBACK) {

    inner class PopularViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Movie){
            with(binding){
                ivPoster.loadMovieImage(item.poster)
                tvTitle.text = item.title
                tvVote.text = AppUtils.getVoteFormat(item.vote)

                item.genres.forEach {
                    containerGenre.addView(createGenreChip(itemView.context, it))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}