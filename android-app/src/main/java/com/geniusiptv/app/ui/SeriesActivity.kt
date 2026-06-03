package com.geniusiptv.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
from com.geniusiptv.app.databinding.ActivitySeriesBinding

class SeriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupSeries()
    }

    private fun setupSeries() {
        // سيتم إضافة قائمة المسلسلات هنا
    }
}