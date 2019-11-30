package id.dikisiswanto.jetpackacademy.data;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;
import id.dikisiswanto.jetpackacademy.utils.JsonHelper;

import static org.junit.Assert.assertNotNull;

public class RemoteTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	private JsonHelper jsonHelper;

	@Before
	public void setUp() {
		Application application = ApplicationProvider.getApplicationContext();
		jsonHelper = new JsonHelper(application);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void loadMovies() {
		List<MovieResponse> expectedMovies = jsonHelper.loadMovies();

		assertNotNull(expectedMovies);
	}

	@Test
	public void loadTvShows() {
		List<MovieResponse> expectedTvShows = jsonHelper.loadMovies();

		assertNotNull(expectedTvShows);
	}
}
