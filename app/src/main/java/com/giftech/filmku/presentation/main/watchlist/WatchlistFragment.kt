package com.giftech.filmku.presentation.main.watchlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.giftech.filmku.databinding.FragmentWatchlistBinding
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.presentation.main.watchlist.adapter.WatchListAdapter
import com.giftech.filmku.utils.Constant.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : Fragment() {
    private val viewModel: WatchlistViewModel by viewModels()

    private var _binding: FragmentWatchlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var watchListAdapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(WatchlistViewModel::class.java)

        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            setupAdapter()
            getData()
        }
    }

    private fun getData() {
        viewModel.watchlist.observe(viewLifecycleOwner){
            watchListAdapter.submitList(it)
        }
    }

    private fun setupAdapter() {
        watchListAdapter = WatchListAdapter { movie ->
            val intent = Intent(requireContext(),DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.rvWatchlist.adapter = watchListAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}