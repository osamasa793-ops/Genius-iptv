package com.geniusiptv.app.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.geniusiptv.app.databinding.ActivityChannelsBinding
import com.geniusiptv.app.utils.VideoPlayerManager
import com.geniusiptv.app.repository.ContentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChannelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelsBinding
    private lateinit var videoPlayerManager: VideoPlayerManager
    private val repository = ContentRepository()
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

        videoPlayerManager = VideoPlayerManager(binding.videoPlayer)
        setupListeners()
        loadChannels()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadChannels() {
        scope.launch {
            try {
                val channels = repository.getChannels()
                if (channels.isNotEmpty()) {
                    val firstChannel = channels[0]
                    playChannel(firstChannel)
                } else {
                    Toast.makeText(this@ChannelsActivity, "لا توجد قنوات", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ChannelsActivity, "خطأ في تحميل القنوات", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playChannel(channel: com.geniusiptv.app.data.Channel) {
        binding.channelNameText.text = channel.name
        binding.channelDescriptionText.text = channel.description
        videoPlayerManager.playStream(channel.streamUrl)
    }

    override fun onPause() {
        super.onPause()
        videoPlayerManager.pause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayerManager.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayerManager.release()
    }
}