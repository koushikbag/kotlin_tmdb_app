package com.example.mymoviedb.network

import com.example.mymoviedb.model.PopularMoviesEntity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import timber.log.Timber

private const val BASE_URL = "https://api.themoviedb.org/3/"


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object TmdbAPI {
    /*val tmdbAPIService: TmdbAPIService by lazy {
        Timber.i("call came to initilization")
        retrofit.create(TmdbAPIService::class.java)
    }*/
    val tmdbAPIService = retrofit.create(TmdbAPIService::class.java)
}


/**
 * A public interface that exposes the [getProperties] method
 */
interface TmdbAPIService {
    /**
     * Returns a Coroutine [Deferred] [List] of [PopularMoviesEntity] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("movie/popular")
    fun getPopularMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Deferred<PopularMoviesEntity>
}