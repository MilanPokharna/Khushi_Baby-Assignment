package com.example.khushibaby.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.example.khushibaby.KhushiBabyApplication
import com.example.khushibaby.databinding.MainActivityBinding
import com.example.khushibaby.factory.MyViewModelFactory
import com.example.khushibaby.models.Movie
import com.example.khushibaby.paging.MoviePagingAdapter
import com.example.khushibaby.repository.MainRepository
import com.example.khushibaby.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: MainRepository
    private lateinit var binding: MainActivityBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MoviePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(repository)).get(MainViewModel::class.java)

        adapter = MoviePagingAdapter(this)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this, Observer { pagingData ->
            adapter.submitData(lifecycle,pagingData)
        })
    }
}
