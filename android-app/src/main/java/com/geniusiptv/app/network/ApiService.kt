package com.geniusiptv.app.network

import retrofit2.http.*
import com.geniusiptv.app.data.Channel
import com.geniusiptv.app.data.Movie
import com.geniusiptv.app.data.Series
import com.geniusiptv.app.data.User

interface ApiService {

    // =============== القنوات ===============
    @GET("channels")
    suspend fun getChannels(): List<Channel>

    @GET("channels/{id}")
    suspend fun getChannel(@Path("id") id: String): Channel

    @POST("channels")
    suspend fun addChannel(@Body channel: Channel): Channel

    @PUT("channels/{id}")
    suspend fun updateChannel(@Path("id") id: String, @Body channel: Channel): Channel

    @DELETE("channels/{id}")
    suspend fun deleteChannel(@Path("id") id: String): Boolean

    // =============== الأفلام ===============
    @GET("movies")
    suspend fun getMovies(): List<Movie>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: String): Movie

    @POST("movies")
    suspend fun addMovie(@Body movie: Movie): Movie

    @PUT("movies/{id}")
    suspend fun updateMovie(@Path("id") id: String, @Body movie: Movie): Movie

    @DELETE("movies/{id}")
    suspend fun deleteMovie(@Path("id") id: String): Boolean

    // =============== المسلسلات ===============
    @GET("series")
    suspend fun getSeries(): List<Series>

    @GET("series/{id}")
    suspend fun getSeriesById(@Path("id") id: String): Series

    @POST("series")
    suspend fun addSeries(@Body series: Series): Series

    @PUT("series/{id}")
    suspend fun updateSeries(@Path("id") id: String, @Body series: Series): Series

    @DELETE("series/{id}")
    suspend fun deleteSeries(@Path("id") id: String): Boolean

    // =============== المستخدمين ===============
    @GET("users/{code}")
    suspend fun getUserByCode(@Path("code") code: String): User

    @POST("users")
    suspend fun registerUser(@Body user: User): User

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): User

    // =============== التحقق من الاتصال ===============
    @GET("health")
    suspend fun checkConnection(): Map<String, String>
}