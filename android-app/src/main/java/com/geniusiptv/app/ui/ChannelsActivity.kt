package com.geniusiptv.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
from com.geniusiptv.app.databinding.ActivityChannelsBinding

class ChannelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupChannels()
    }

    private fun setupChannels() {
        // سيتم إضافة قائمة القنوات هنا
        // تصنيفات: رياضة، أخبار، أطفال، أردني، سوري، خليجي
    }
}