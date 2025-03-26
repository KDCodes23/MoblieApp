package com.assignment2.movieApp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment2.movieApp.adapter.MovieAdapter
import com.assignment2.movieApp.databinding.ActivityMovieSearchBinding
import com.assignment2.movieApp.viewmodel.MovieViewModel

class MovieSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieSearchBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        // Setup RecyclerView
        movieAdapter = MovieAdapter(emptyList()) { selectedMovie ->

        }
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMovies.adapter = movieAdapter

        // Setup Search Button
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearchMovie.text.toString()
            viewModel.searchMovies(query)
        }

        // Observe Movies
        viewModel.movies.observe(this) { movies ->
            movieAdapter.updateMovies(movies)
        }
    }
}
