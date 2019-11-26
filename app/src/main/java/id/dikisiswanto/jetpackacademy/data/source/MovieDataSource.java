package id.dikisiswanto.jetpackacademy.data.source;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public interface MovieDataSource {
	List<MovieEntity> getAllMovies();

	MovieEntity getMovieById(String movieId);

	List<MovieEntity> getAllTvShows();

	MovieEntity getTvShowById(String tvShowId);
}
