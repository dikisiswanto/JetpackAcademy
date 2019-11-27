package id.dikisiswanto.jetpackacademy.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public class MovieViewModel extends ViewModel {

	private MovieRepository movieRepository;

	public MovieViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public LiveData<List<MovieEntity>> getMovies() {
		return movieRepository.getAllMovies();
	}
}
