package id.dikisiswanto.jetpackacademy.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public interface MovieDataSource {
	LiveData<Resource<List<MovieEntity>>> getAllMovies();

	LiveData<Resource<MovieEntity>> getMovieById(String movieId);

	LiveData<Resource<List<MovieEntity>>> getAllTvShows();

	LiveData<Resource<MovieEntity>> getTvShowById(String tvShowId);

	LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies();

	LiveData<Resource<PagedList<MovieEntity>>> getFavoriteTvShows();

	void setFavoriteStatus(MovieEntity movieEntity);
}
