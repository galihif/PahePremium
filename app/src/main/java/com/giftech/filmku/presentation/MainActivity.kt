package com.giftech.filmku.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.giftech.filmku.R
import com.giftech.filmku.core.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val msg = viewModel.message
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        viewModel.nowPlaying.observe(this){
            when(it){
                is Resource.Error -> Log.d("GALIH", it.error)
                is Resource.Loading -> Log.d("GALIH","loading")
                is Resource.Success -> {
                    it.data.forEach{
                        Log.d("GALIH", it.id.toString())
                    }
                }
            }
        }
    }
}