package com.giftech.filmku.presentation.main.home

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
import com.giftech.filmku.presentation.main.home.adapter.NowPlayingAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel:HomeViewModel by viewModels()
    private lateinit var adapter: NowPlayingAdapter

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
        }
    }

    private fun getData() {
        viewModel.nowPlaying.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> Log.d("GALIH", it.error)
                is Resource.Loading -> Log.d("GALIH", "loading")
                is Resource.Success -> adapter.submitList(it.data)
            }
        }
    }

    private fun setupAdapter() {
        adapter = NowPlayingAdapter { movie ->
            Toast.makeText(activity, movie.title, Toast.LENGTH_SHORT).show()
        }
        binding.rvCategories.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}