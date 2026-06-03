package com.geniusiptv.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geniusiptv.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupListeners()
    }

    private fun setupListeners() {
        // الذهاب إلى القنوات
        binding.channelsCard.setOnClickListener {
            startActivity(Intent(this, ChannelsActivity::class.java))
        }

        // الذهاب إلى الأفلام
        binding.moviesCard.setOnClickListener {
            startActivity(Intent(this, MoviesActivity::class.java))
        }

        // الذهاب إلى المسلسلات
        binding.seriesCard.setOnClickListener {
            startActivity(Intent(this, SeriesActivity::class.java))
        }

        // الذهاب إلى الإعدادات
        binding.settingsCard.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}