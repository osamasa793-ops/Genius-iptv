package com.geniusiptv.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniusiptv.app.databinding.ActivityChannelsListBinding
import com.geniusiptv.app.repository.ContentRepository
import com.geniusiptv.app.adapter.ChannelsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChannelsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChannelsListBinding
    private val repository = ContentRepository()
    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var channelsAdapter: ChannelsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChannelsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupRecyclerView()
        loadChannels()
        setupCategoryFilter()
        setupListeners()
    }

    private fun setupRecyclerView() {
        channelsAdapter = ChannelsAdapter { channel ->
            // تشغيل القناة
            val intent = Intent(this, ChannelsActivity::class.java).apply {
                putExtra("channel_id", channel.id)
                putExtra("channel_name", channel.name)
                putExtra("stream_url", channel.streamUrl)
            }
            startActivity(intent)
        }
        binding.channelsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChannelsListActivity)
            adapter = channelsAdapter
        }
    }

    private fun loadChannels() {
        scope.launch {
            try {
                val channels = repository.getChannels()
                channelsAdapter.submitList(channels)
            } catch (e: Exception) {
                Toast.makeText(this@ChannelsListActivity, "خطأ في تحميل القنوات", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCategoryFilter() {
        binding.categorySpinner.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position).toString()
                filterByCategory(selectedCategory)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        })
    }

    private fun filterByCategory(category: String) {
        scope.launch {
            try {
                val channels = repository.getChannels()
                val filteredChannels = if (category == "الكل") {
                    channels
                } else {
                    channels.filter { it.category == category }
                }
                channelsAdapter.submitList(filteredChannels)
            } catch (e: Exception) {
                Toast.makeText(this@ChannelsListActivity, "خطأ في التصفية", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
