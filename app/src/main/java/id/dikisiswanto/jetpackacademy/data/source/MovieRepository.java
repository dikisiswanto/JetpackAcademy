package id.dikisiswanto.jetpackacademy.data.source;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public class MovieRepository implements MovieDataSource {
	private volatile static MovieRepository INSTANCE = null;

	@Override
	public List<MovieEntity> getAllMovies() {
		return null;
	}

	@Override
	public MovieEntity getMovieById(String movieId) {
		return null;
	}

	@Override
	public List<MovieEntity> getAllTvShows() {
		return null;
	}

	@Override
	public MovieEntity getTvShowById(String tvShowId) {
		return null;
	}
}
