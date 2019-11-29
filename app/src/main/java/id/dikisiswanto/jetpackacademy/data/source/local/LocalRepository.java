package id.dikisiswanto.jetpackacademy.data.source.local;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.local.room.MovieDao;

public class LocalRepository {
	private static LocalRepository INSTANCE;
	private final MovieDao movieDao;

	private LocalRepository(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public static LocalRepository getInstance(MovieDao movieDao) {
		if (INSTANCE == null) {
			INSTANCE = new LocalRepository(movieDao);
		}
		return INSTANCE;
	}

	public LiveData<List<MovieEntity>> getAllMovies() {
		return movieDao.getMovies();
	}

	public LiveData<MovieEntity> getMovieById(String id) {
		return movieDao.getMovieById(id);
	}

	public LiveData<List<MovieEntity>> getAllTvShows() {
		return movieDao.getTvShows();
	}

	public LiveData<MovieEntity> getTvShowById(String id) {
		return movieDao.getTvShowById(id);
	}

	public LiveData<List<MovieEntity>> getFavoriteMovies() {
		return movieDao.getFavoriteMovies();
	}

	public LiveData<List<MovieEntity>> getFavoriteTvShows() {
		return movieDao.getFavoriteTvShows();
	}

	public void insertMovies(List<MovieEntity> movies) {
		movieDao.insertMovie(movies);
	}

	public void updateFavoriteStatus(MovieEntity movie) {
		movieDao.updateMovie(movie);
	}

}
