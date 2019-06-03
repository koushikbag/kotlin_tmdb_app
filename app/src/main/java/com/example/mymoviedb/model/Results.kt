package com.example.mymoviedb.model

//@Parcelize
data class Results(
    val vote_count: String,
    val id: String,
    val video: Boolean,
    val vote_average: String,
    val title: String,
    val popularity: String,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val adult: Boolean
) {
    val rating
        get() = "$vote_average/10"
}