package com.giftech.filmku.watchlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.giftech.filmku.di.WatchlistModuleDependencies
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.presentation.main.watchlist.adapter.WatchListAdapter
import com.giftech.filmku.utils.Constant
import com.giftech.filmku.watchlist.databinding.ActivityWatchlistBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

//@AndroidEntryPoint
class WatchlistActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWatchlistBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel:WatchlistViewModel by viewModels{factory}
    private lateinit var watchListAdapter: WatchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerWatchlistComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    WatchlistModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        setupAdapter()
        getData()
    }

    private fun getData() {
        viewModel.watchlist.observe(this){
            watchListAdapter.submitList(it)
        }
    }

    private fun setupAdapter() {
        watchListAdapter = WatchListAdapter { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(Constant.MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.rvWatchlist.adapter = watchListAdapter
    }

}