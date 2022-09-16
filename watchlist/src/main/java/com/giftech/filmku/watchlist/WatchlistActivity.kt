package com.giftech.filmku.watchlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.giftech.filmku.di.WatchlistModuleDependencies
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.watchlist.adapter.WatchListAdapter
import com.giftech.filmku.utils.Constant
import com.giftech.filmku.watchlist.databinding.ActivityWatchlistBinding
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
        setupHilt()
        setupToolbar()
        setupAdapter()
        getData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null){
            supportActionBar!!.title = "Watchlist"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
        }
    }

    private fun setupHilt() {
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
    }

    private fun getData() {
        viewModel.watchlist.observe(this){
            watchListAdapter.submitList(it)
            showEmpty(it.isEmpty())
        }
    }

    private fun showEmpty(empty: Boolean) {
        binding.empty.visibility = if (empty) View.VISIBLE else View.GONE
        binding.emptyMessage.visibility = if (empty) View.VISIBLE else View.GONE
    }

    private fun setupAdapter() {
        watchListAdapter = WatchListAdapter { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(Constant.MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.rvWatchlist.adapter = watchListAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}