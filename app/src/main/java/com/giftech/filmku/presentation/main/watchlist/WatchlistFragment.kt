package com.giftech.filmku.presentation.main.watchlist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.giftech.filmku.R
import com.giftech.filmku.databinding.FragmentSearchBinding
import com.giftech.filmku.databinding.FragmentWatchlistBinding
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.presentation.main.watchlist.adapter.WatchListAdapter
import com.giftech.filmku.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel:WatchlistViewModel by viewModels()
    private lateinit var watchListAdapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!=null){
            setupAdapter()
            getData()
        }
    }

    private fun getData() {
        viewModel.watchlist.observe(viewLifecycleOwner){
            watchListAdapter.submitList(it)
            showEmpty(it.isEmpty())
        }
    }

    private fun showEmpty(empty: Boolean) {
//        binding.empty.visibility = if (empty) View.VISIBLE else View.GONE
//        binding.emptyMessage.visibility = if (empty) View.VISIBLE else View.GONE
    }

    private fun setupAdapter() {
        watchListAdapter = WatchListAdapter { movie ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constant.MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.rvWatchlist.adapter = watchListAdapter
    }
}