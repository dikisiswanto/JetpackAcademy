package id.dikisiswanto.jetpackacademy.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

@Dao
public interface MovieDao {

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 1")
	LiveData<List<MovieEntity>> getMovies();

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 1 AND id = :id")
	LiveData<MovieEntity> getMovieById(String id);

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 2")
	LiveData<List<MovieEntity>> getTvShows();

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 2 AND id = :id")
	LiveData<MovieEntity> getTvShowById(String id);

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 1 AND status = 1")
	DataSource.Factory<Integer, MovieEntity> getFavoriteMovies();

	@WorkerThread
	@Query("SELECT * FROM movie_entities WHERE type = 2 AND status = 1")
	DataSource.Factory<Integer, MovieEntity> getFavoriteTvShows();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	long[] insertMovies(List<MovieEntity> movies);

	@Update
	int updateMovie(MovieEntity movie);

	@Delete
	int deleteMovie(MovieEntity movie);
}
