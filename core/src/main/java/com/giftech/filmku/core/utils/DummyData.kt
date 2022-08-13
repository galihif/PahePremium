package com.giftech.filmku.core.utils

import com.giftech.filmku.core.domain.model.Movie

object DummyData {
    fun getListMovie():List<Movie>{
        val listMovie = ArrayList<Movie>()
        for(i in 1..10){
            listMovie.add(
                Movie(
                    id = i,
                    title = "Tes",
                    description = "desc",
                    poster = "",
                    genres = arrayListOf("1","2","3"),
                    vote = 9.0
                )
            )
        }
        return listMovie
    }
}