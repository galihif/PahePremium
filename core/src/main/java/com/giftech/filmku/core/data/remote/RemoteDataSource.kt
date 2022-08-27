package com.giftech.filmku.core.data.remote

import com.giftech.filmku.core.BuildConfig
import com.giftech.filmku.core.data.remote.dto.toMovie
import com.giftech.filmku.core.data.remote.network.ApiService
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Constants
import com.giftech.filmku.core.utils.DummyData
import com.giftech.filmku.core.utils.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api:ApiService
) {
    fun getTest() = "Coming soon"

    fun getNowPlaying():Flowable<Resource<List<Movie>>> {
        val resultData = PublishSubject.create<Resource<List<Movie>>>()
        val client = api.getNowPlaying()
        resultData.onNext(Resource.Loading())
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response ->
                val data = response.results.map { it.toMovie() }
                resultData.onNext(
                    Resource.Success(data)
                )
            },{error ->
                resultData.onNext(Resource.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPopular():Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            try {
                val res = api.getPopular().results.map { it.toMovie() }
                emit(Resource.Success(res))
            }catch (e: HttpException){
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            }catch (e: IOException){
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieDetail(movieId:Int):Flow<Resource<Movie>> =
        flow {
            emit(Resource.Loading())
            try {
                val res = api.getMovieDetail(movieId).toMovie()
                emit(Resource.Success(res))
            }catch (e: HttpException){
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            }catch (e: IOException){
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)




}