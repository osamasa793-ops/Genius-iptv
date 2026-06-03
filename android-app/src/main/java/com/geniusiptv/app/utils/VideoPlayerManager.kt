package com.geniusiptv.app.utils

import android.media.MediaPlayer
import android.widget.VideoView
import android.net.Uri

class VideoPlayerManager(private val videoView: VideoView) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0

    fun playStream(streamUrl: String) {
        try {
            videoView.apply {
                setVideoURI(Uri.parse(streamUrl))
                requestFocus()
                setOnPreparedListener { mp ->
                    mediaPlayer = mp
                    // تشغيل من آخر موضع
                    if (currentPosition > 0) {
                        seekTo(currentPosition)
                    }
                    start()
                }
                setOnErrorListener { _, what, extra ->
                    android.util.Log.e("VideoPlayer", "Error: $what, $extra")
                    false
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("VideoPlayer", "Error playing stream: ${e.message}")
        }
    }

    fun pause() {
        videoView.pause()
        currentPosition = videoView.currentPosition
    }

    fun resume() {
        videoView.resume()
    }

    fun stop() {
        videoView.stopPlayback()
        currentPosition = 0
    }

    fun getCurrentPosition(): Int {
        return videoView.currentPosition
    }

    fun getDuration(): Int {
        return videoView.duration
    }

    fun release() {
        mediaPlayer?.release()
        videoView.stopPlayback()
    }
}