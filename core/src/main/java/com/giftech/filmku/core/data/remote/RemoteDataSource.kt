package com.giftech.filmku.core.data.remote

import com.giftech.filmku.core.BuildConfig
import com.giftech.filmku.core.data.remote.dto.toMovie
import com.giftech.filmku.core.data.remote.network.ApiService
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Constants
import com.giftech.filmku.core.utils.DummyData
import com.giftech.filmku.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    fun getTest() = "Coming soon"

    fun getNowPlaying(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getNowPlaying().results.map { it.toMovie() }
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getPopular(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getPopular().results.map { it.toMovie() }
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getSearchResult(query: String): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getSearchResult(query).results.map { it.toMovie() }
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieDetail(movieId: Int): Flow<Resource<Movie>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getMovieDetail(movieId).toMovie()
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

}