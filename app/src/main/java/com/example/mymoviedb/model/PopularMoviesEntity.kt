package com.example.mymoviedb.model

import com.squareup.moshi.Json

//@Parcelize
data class PopularMoviesEntity(
    val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<Results>?
)