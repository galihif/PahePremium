package com.giftech.filmku.presentation.main.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.giftech.filmku.core.utils.Resource
import com.giftech.filmku.databinding.FragmentSearchBinding
import com.giftech.filmku.presentation.detail.DetailActivity
import com.giftech.filmku.presentation.main.search.adapter.SearchResultAdapter
import com.giftech.filmku.utils.Constant.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel:SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            setOnSearch()
            initAdapter()
            getMovieResult()
        }
    }

    private fun getMovieResult() {
        viewModel.movieResults.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> Toast.makeText(activity, it.error, Toast.LENGTH_SHORT).show()
                Resource.Loading -> Log.d("galih", "getMovieResult: loading")
                is Resource.Success -> {
                    searchResultAdapter.submitList(it.data)
                    Log.d("galih", "getMovieResult: ${it.data.size}")
                }
            }
        }
    }

    private fun initAdapter() {
        searchResultAdapter = SearchResultAdapter{movie ->

        }
        binding.rvResult.adapter = searchResultAdapter
    }

    private fun setOnSearch() {
        binding.etSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                viewModel.setKeyword(textView.text.toString())
                clearEditTextFocus()
            }
            true
        }
    }

    private fun clearEditTextFocus() {
        binding.etSearch.clearFocus()
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}