package com.assignment2.movieApp.network

import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

object MovieApiClient {
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val API_KEY = "afdecc7a"

    fun searchMovies(query: String): String? {
        val urlString = "$BASE_URL?apikey=$API_KEY&s=$query"
        return makeRequest(urlString)
    }

    fun getMovieDetails(imdbId: String): String? {
        val urlString = "$BASE_URL?apikey=$API_KEY&i=$imdbId"
        return makeRequest(urlString)
    }

    private fun makeRequest(urlString: String): String? {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()
            reader.close()
            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
