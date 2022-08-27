package com.giftech.filmku.core.data.remote.network

import com.giftech.filmku.core.data.remote.dto.GetMovieDetailDto
import com.giftech.filmku.core.data.remote.dto.GetNowPlayingDto
import com.giftech.filmku.core.data.remote.dto.GetPopularDto
import com.giftech.filmku.core.utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey:String = Constants.API_KEY
    ):Flowable<GetNowPlayingDto>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey:String = Constants.API_KEY
    ):GetPopularDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId:Int,
        @Query("api_key") apiKey:String = Constants.API_KEY
    ):GetMovieDetailDto



}