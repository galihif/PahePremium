package com.giftech.filmku.core.utils

import com.giftech.filmku.core.data.local.entity.MovieEntity
import com.giftech.filmku.core.domain.model.Movie

object Mapper {
    fun domainToEntity(movie: Movie):MovieEntity =
        MovieEntity(
            id = movie.id,
            title = movie.title,
            description = movie.description,
            poster = movie.poster,
            backdrop = movie. backdrop,
            vote = movie.vote,
            language = movie.language,
            genres = movie.genres,
            length = movie. length,
        )

    fun entitiesToDomain(input:List<MovieEntity>):List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                description = it.description,
                poster = it.poster,
                backdrop = it.backdrop,
                vote = it.vote,
                language = it.language,
                genres = it.genres,
                length = it.length,
            )
        }
}