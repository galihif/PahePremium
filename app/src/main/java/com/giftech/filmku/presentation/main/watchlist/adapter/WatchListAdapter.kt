package com.giftech.filmku.presentation.main.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.databinding.ItemMovieBinding
import com.giftech.filmku.utils.AppUtils
import com.giftech.filmku.utils.AppUtils.createGenreChip
import com.giftech.filmku.utils.AppUtils.loadMovieImage

class WatchListAdapter(
    private val onClickItem:(Movie) -> Unit
): ListAdapter<Movie, WatchListAdapter.WatchlistViewHolder>(DIFF_CALLBACK) {

    inner class WatchlistViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Movie){
            with(binding){
                ivPoster.loadMovieImage(item.poster)
                tvTitle.text = item.title
                tvVote.text = AppUtils.getVoteFormat(item.vote)

                containerGenre.removeAllViews()
                item.genres.forEach {
                    containerGenre.addView(createGenreChip(itemView.context, it))
                }

                itemView.setOnClickListener { onClickItem(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
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