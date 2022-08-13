package com.giftech.filmku.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object AppUtils {
    fun ImageView.loadMovieImage(imageSource : String?) {
        val path = "https://image.tmdb.org/t/p/w185/$imageSource"
        Glide.with(context)
            .load(path)
            .into(this)
    }

    fun getVoteFormat(vote:Double) = "$vote/10 IMDb"
}