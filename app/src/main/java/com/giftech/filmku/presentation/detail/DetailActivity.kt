package com.giftech.filmku.presentation.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import com.giftech.filmku.R
import com.giftech.filmku.core.domain.model.Movie
import com.giftech.filmku.core.utils.Resource
import com.giftech.filmku.databinding.ActivityDetailBinding
import com.giftech.filmku.utils.AppUtils
import com.giftech.filmku.utils.AppUtils.applyFilter
import com.giftech.filmku.utils.AppUtils.getTimeFormat
import com.giftech.filmku.utils.AppUtils.getVoteFormat
import com.giftech.filmku.utils.AppUtils.loadMovieImage
import com.giftech.filmku.utils.Constant.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private val viewModel:DetailViewModel by viewModels()
    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMovieId()
        getMovie()
        setButtonClick()

        viewModel.toastText.observe(this){
            if(it.isNotEmpty()){
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isWatchList.observe(this){
            setBtnSaveView(it)
        }
    }

    private fun setBtnSaveView(isWatchList: Boolean) {
        binding.btnSave.setImageResource(
            if (isWatchList) R.drawable.ic_save_filled
            else R.drawable.ic_save
        )
    }

    private fun setButtonClick() {
        with(binding){
            btnBack.setOnClickListener { onBackPressed() }
            btnSave.setOnClickListener { viewModel.saveMovie(movie) }
        }
    }

    private fun getMovie() {
        viewModel.movie.observe(this){
            when(it){
                is Resource.Error -> {
                    showLoading(false)
                }
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    populateView(it.data)
                    movie = it.data
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding){
            ivPlay.visibility = if(isLoading) View.INVISIBLE else View.VISIBLE
            tvWatch.visibility = if(isLoading) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun populateView(movie: Movie) {
        with(binding){
            ivBackdrop.loadMovieImage(movie.backdrop)
            ivBackdrop.applyFilter(ivBackdrop)
            tvTitle.text = movie.title
            tvVote.text = getVoteFormat(movie.vote)
            tvLength.text = getTimeFormat(movie.length)
            tvLang.text = movie.language
            tvDesc.text = movie.description

            movie.genres.forEach {
                containerGenre.addView(AppUtils.createGenreChip(this@DetailActivity, it))
            }
        }
    }

    private fun setMovieId() {
        if (intent.extras != null){
            val movieId = intent.extras?.getInt(MOVIE_ID)
            viewModel.setTaskId(movieId ?: 0)
        }
    }

}