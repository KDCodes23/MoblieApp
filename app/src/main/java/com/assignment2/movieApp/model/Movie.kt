package com.assignment2.movieApp.model

data class Movie(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
    val plot: String? = "null",
    val rated: String? = "null"
)