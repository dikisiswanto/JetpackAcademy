package id.dikisiswanto.jetpackacademy.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public class TvShowViewModel extends ViewModel {
	private MovieRepository movieRepository;

	public TvShowViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public LiveData<List<MovieEntity>> getTvShows() {
		return movieRepository.getAllTvShows();
	}
}
