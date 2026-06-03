package com.geniusiptv.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
from com.geniusiptv.app.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupMovies()
    }

    private fun setupMovies() {
        // سيتم إضافة قائمة الأفلام هنا
    }
}