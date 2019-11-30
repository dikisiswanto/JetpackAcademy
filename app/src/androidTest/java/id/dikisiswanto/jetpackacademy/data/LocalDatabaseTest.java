package id.dikisiswanto.jetpackacademy.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.local.room.MovieDatabase;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;
import id.dikisiswanto.jetpackacademy.utils.LiveDataTestUtil;

import static org.junit.Assert.assertEquals;

public class LocalDatabaseTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	private MovieDatabase db;

	@Before
	public void setUp() {
		db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
				MovieDatabase.class)
				.allowMainThreadQueries()
				.build();
	}

	@After
	public void tearDown() {
		db.close();
	}

	@Test
	public void insertAllMoviesTest() {
		List<MovieEntity> expectedMovies = FakeDataDummy.getMovies();
		db.movieDao().insertMovies(expectedMovies);

		List<MovieEntity> actualMovies = LiveDataTestUtil.getValue(db.movieDao().getMovies());

		assertEquals(expectedMovies.size(), actualMovies.size());
	}

	@Test
	public void insertAllTvShowsTest() {
		List<MovieEntity> expectedTvShows = FakeDataDummy.getTvShows();
		db.movieDao().insertMovies(expectedTvShows);

		List<MovieEntity> actualTvShows = LiveDataTestUtil.getValue(db.movieDao().getTvShows());

		assertEquals(expectedTvShows.size(), actualTvShows.size());
	}
}
