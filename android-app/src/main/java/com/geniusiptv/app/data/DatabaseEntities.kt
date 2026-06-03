package com.geniusiptv.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class ChannelEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val streamUrl: String,
    val description: String
)

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val streamUrl: String,
    val description: String,
    val releaseDate: String
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val activationCode: String,
    val expiryDate: Long,
    val isActive: Boolean,
    val createdDate: Long
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val itemId: String,
    val itemType: String, // "channel", "movie", "series"
    val addedDate: Long
)