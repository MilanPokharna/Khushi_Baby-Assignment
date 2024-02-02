package com.example.khushibaby.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.IntentCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.khushibaby.R
import com.example.khushibaby.databinding.ActivityDetailBinding
import com.example.khushibaby.databinding.MainActivityBinding
import com.example.khushibaby.models.Movie

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie : Movie = IntentCompat.getParcelableExtra(intent, "Movie", Movie::class.java)!!

        binding.name.text = movie.title
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+ movie.backdrop_path)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.loading)
            .into(binding.poster)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+ movie.poster_path)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.loading)
            .into(binding.image)

        binding.rating.text = movie.vote_average.toString()
        binding.voteCount.text = "("+movie.vote_count+" votes)"
        binding.overviewText.text = movie.overview
        binding.releaseDate.text = movie.release_date

    }
}