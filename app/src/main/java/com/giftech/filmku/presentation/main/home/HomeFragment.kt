package com.giftech.filmku.presentation.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.giftech.filmku.core.utils.Resource
import com.giftech.filmku.databinding.FragmentHomeBinding
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.presentation.main.home.adapter.NowPlayingAdapter
import com.giftech.filmku.presentation.main.home.adapter.PopularAdapter
import com.giftech.filmku.utils.Category
import com.giftech.filmku.utils.Constant.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel:HomeViewModel by viewModels()
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var popularAdapter: PopularAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            setupAdapter()
            getData()
            setButtonClick()
        }
    }

    private fun setButtonClick() {
        binding.btnWatchlist.setOnClickListener {
            val uri = Uri.parse("filmku://watchlist")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun getData() {
        viewModel.nowPlaying.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    showLoading(false,Category.NOW_PLAYING)
                }
                is Resource.Loading -> showLoading(true, Category.NOW_PLAYING)
                is Resource.Success -> {
                    showLoading(false,Category.NOW_PLAYING)
                    nowPlayingAdapter.submitList(it.data)
                }
            }
        }
        viewModel.popular.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    showLoading(false,Category.POPULAR)
                }
                is Resource.Loading -> showLoading(true, Category.POPULAR)
                is Resource.Success -> {
                    showLoading(false,Category.POPULAR)
                    popularAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean, category: Category) {
        when(category){
            Category.NOW_PLAYING -> {
                binding.nowPlayingLoad.root.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            Category.POPULAR -> {
                binding.popularLoad.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupAdapter() {
        nowPlayingAdapter = NowPlayingAdapter { movie ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.nowPlaying.rvNowPlaying.adapter = nowPlayingAdapter

        popularAdapter = PopularAdapter{ movie ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movie.id)
            startActivity(intent)
        }
        binding.rvPopular.adapter = popularAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}