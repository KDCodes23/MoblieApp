import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.assignment2.movieApp.databinding.ActivityMovieDetailsBinding
import com.assignment2.movieApp.viewmodel.MovieViewModel
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)


        // Get Movie ID from Intent and fetch details
        val movieId = intent.getStringExtra("MOVIE_ID")
        movieId?.let { id ->
            viewModel.getMovieDetails(id)
        }

        // Observe LiveData for movie details
        viewModel.movieDetailsLiveData.observe(this) { movie ->
            // Update UI with movie details
            binding.apply {
                tvMovieTitle.text = movie.title
                tvMovieYear.text = movie.year
                tvMovieRating.text = movie.rated
                tvMovieDescription.text = movie.plot
                Glide.with(this@MovieDetailsActivity)
                    .load(movie.poster)
                    .into(ivMoviePoster)
            }
        }

        // Setup back button
        binding.btnGoBack.setOnClickListener { finish() }
    }
}
