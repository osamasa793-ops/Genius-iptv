package com.geniusiptv.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geniusiptv.app.databinding.ActivityChannelsListBinding

class ChannelsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupListeners()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}