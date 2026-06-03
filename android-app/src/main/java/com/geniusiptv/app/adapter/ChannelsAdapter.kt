package com.geniusiptv.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geniusiptv.app.data.Channel
import com.geniusiptv.app.databinding.ItemChannelBinding

class ChannelsAdapter(
    private val onChannelClick: (Channel) -> Unit
) : ListAdapter<Channel, ChannelsAdapter.ChannelViewHolder>(ChannelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val binding = ItemChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChannelViewHolder(binding, onChannelClick)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChannelViewHolder(
        private val binding: ItemChannelBinding,
        private val onChannelClick: (Channel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(channel: Channel) {
            binding.channelName.text = channel.name
            binding.channelCategory.text = channel.category
            binding.channelDescription.text = channel.description
            // يمكن إضافة تحميل الصورة هنا بـ Glide
            binding.root.setOnClickListener { onChannelClick(channel) }
        }
    }

    class ChannelDiffCallback : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Channel, newItem: Channel) = oldItem == newItem
    }
}
