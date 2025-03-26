package com.assignment2.movieApp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.assignment2.movieApp.databinding.ItemMovieBinding
import com.assignment2.movieApp.model.Movie

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun updateMovies(newMovies: List<Movie>) {
        Log.d("MovieAdapter", "Updating movies: $newMovies")
        this.movies = newMovies
        notifyDataSetChanged()  // Notify the RecyclerView of the data change
    }
    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvMovieTitle.text = movie.title
                tvMovieYear.text = movie.year

                // Load poster image
                Glide.with(itemView.context)
                    .load(movie.poster)  // `movie.poster` should contain the URL to the image
                    .into(binding.ivMoviePoster)  // Reference to your ImageView

                // Set click listener
                root.setOnClickListener { onItemClick(movie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}
