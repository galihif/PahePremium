package com.giftech.filmku.utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.bumptech.glide.Glide
import com.giftech.filmku.R
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.min

object AppUtils {
    fun ImageView.loadMovieImage(imageSource : String?) {
        val path = "https://image.tmdb.org/t/p/original/$imageSource"
        Glide.with(context)
            .load(path)
            .into(this)
    }

    fun ImageView.applyFilter(image:ImageView){
        val semiTransparentGrey = Color.argb(155, 0, 0, 0);
        image.setColorFilter(semiTransparentGrey, PorterDuff.Mode.SRC_ATOP)
    }

    fun createGenreChip(context:Context, genreName:String):TextView{
        val tvGenre = TextView(context)
        tvGenre.text = genreName
        tvGenre.setBackgroundResource(R.drawable.box_blue_8dp)
        tvGenre.setPadding(16,6,16,6)
        TextViewCompat.setTextAppearance(tvGenre, R.style.genre)
        return tvGenre
    }

    fun getVoteFormat(vote:Double):String {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.FLOOR
        return "${df.format(vote)}/10 IMDb"
    }

    fun getTimeFormat(time:Int):String{
        val hour = time/60
        val minutes = time%60
        return "${hour}h ${minutes}min"
    }
}