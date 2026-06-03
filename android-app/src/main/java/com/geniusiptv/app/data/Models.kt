package com.geniusiptv.app.data

data class User(
    val id: String,
    val username: String,
    val activationCode: String,
    val expiryDate: Long,
    val isActive: Boolean,
    val createdDate: Long
)

data class Channel(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val streamUrl: String,
    val description: String
)

data class Movie(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val streamUrl: String,
    val description: String,
    val releaseDate: String
)

data class Series(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val description: String,
    val episodes: List<Episode>
)

data class Episode(
    val id: String,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val streamUrl: String,
    val title: String
)

data class AdminCredentials(
    val username: String = "Osama1980",
    val password: String = "Miramaya2026",
    val accessToken: String? = null
)