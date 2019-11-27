package id.dikisiswanto.jetpackacademy.data.source;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public interface MovieDataSource {
	LiveData<List<MovieEntity>> getAllMovies();

	LiveData<MovieEntity> getMovieById(String movieId);

	LiveData<List<MovieEntity>> getAllTvShows();

	LiveData<MovieEntity> getTvShowById(String tvShowId);
}
