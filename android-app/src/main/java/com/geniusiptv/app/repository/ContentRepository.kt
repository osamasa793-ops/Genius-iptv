package com.geniusiptv.app.repository

import com.geniusiptv.app.network.RetrofitClient
import com.geniusiptv.app.data.Channel
import com.geniusiptv.app.data.Movie
import com.geniusiptv.app.data.Series
import com.geniusiptv.app.data.User

class ContentRepository {

    private val apiService = RetrofitClient.apiService

    // =============== القنوات ===============
    suspend fun getChannels(): List<Channel> {
        return try {
            apiService.getChannels()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addChannel(channel: Channel): Boolean {
        return try {
            apiService.addChannel(channel)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateChannel(id: String, channel: Channel): Boolean {
        return try {
            apiService.updateChannel(id, channel)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteChannel(id: String): Boolean {
        return try {
            apiService.deleteChannel(id)
        } catch (e: Exception) {
            false
        }
    }

    // =============== الأفلام ===============
    suspend fun getMovies(): List<Movie> {
        return try {
            apiService.getMovies()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addMovie(movie: Movie): Boolean {
        return try {
            apiService.addMovie(movie)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateMovie(id: String, movie: Movie): Boolean {
        return try {
            apiService.updateMovie(id, movie)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteMovie(id: String): Boolean {
        return try {
            apiService.deleteMovie(id)
        } catch (e: Exception) {
            false
        }
    }

    // =============== المسلسلات ===============
    suspend fun getSeries(): List<Series> {
        return try {
            apiService.getSeries()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addSeries(series: Series): Boolean {
        return try {
            apiService.addSeries(series)
            true
        } catch (e: Exception) {
            false
        }
    }

    // =============== المستخدمين ===============
    suspend fun validateUser(activationCode: String): Boolean {
        return try {
            val user = apiService.getUserByCode(activationCode)
            user.isActive && user.expiryDate > System.currentTimeMillis()
        } catch (e: Exception) {
            false
        }
    }

    suspend fun checkServerConnection(): Boolean {
        return try {
            apiService.checkConnection()
            true
        } catch (e: Exception) {
            false
        }
    }
}