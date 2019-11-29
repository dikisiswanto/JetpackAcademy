package id.dikisiswanto.jetpackacademy.data.source;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public interface MovieDataSource {
	LiveData<Resource<List<MovieEntity>>> getAllMovies();

	LiveData<Resource<MovieEntity>> getMovieById(String movieId);

	LiveData<Resource<List<MovieEntity>>> getAllTvShows();

	LiveData<Resource<MovieEntity>> getTvShowById(String tvShowId);

	LiveData<Resource<List<MovieEntity>>> getFavoriteMovies();

	LiveData<Resource<List<MovieEntity>>> getFavoriteTvShows();

	void setFavoriteStatus(MovieEntity movieEntity);
}
