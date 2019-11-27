package id.dikisiswanto.jetpackacademy.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;
import id.dikisiswanto.jetpackacademy.utils.LiveDataTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private RemoteRepository remoteRepository = Mockito.mock(RemoteRepository.class);
	private FakeMovieRepository movieRepository = new FakeMovieRepository(remoteRepository);

	private List<MovieResponse> movieResponses = FakeDataDummy.getRemoteMovies();
	private String movieId = movieResponses.get(0).getId();
	private List<MovieResponse> tvShowResponses = FakeDataDummy.getRemoteTvShows();
	private String tvShowId = tvShowResponses.get(0).getId();

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void getAllMovies() {
		doAnswer(invocation -> {
			((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
					.onAllMoviesReceived(movieResponses);
			return null;
		}).when(remoteRepository).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

		List<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getAllMovies());
		verify(remoteRepository, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

		assertNotNull(result);
		assertEquals(movieResponses.size(), result.size());
	}

	@Test
	public void getMovieById() {
		doAnswer(invocation -> {
			((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
					.onAllMoviesReceived(movieResponses);
			return null;
		}).when(remoteRepository).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

		MovieEntity result = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId));

		verify(remoteRepository, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

		MovieResponse response = movieResponses.get(0);

		assertNotNull(result);
		assertEquals(response.getId(), result.getId());
		assertEquals(response.getTitle(), result.getTitle());
		assertEquals(response.getDescription(), result.getDescription());
		assertEquals(response.getVoteAverage(), result.getVoteAverage());
		assertEquals(response.getReleaseDate(), result.getReleaseDate());
		assertEquals(response.getOriginalLanguage(), result.getOriginalLanguage());
		assertEquals(response.getPoster(), result.getPoster());
	}

	@Test
	public void getAllTvShows() {
		doAnswer(invocation -> {
			((RemoteRepository.LoadTvShowCallback) invocation.getArguments()[0])
					.onAllTvShowsReceived(tvShowResponses);
			return null;
		}).when(remoteRepository).getAllTvShows(any(RemoteRepository.LoadTvShowCallback.class));

		List<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getAllTvShows());
		verify(remoteRepository, times(1)).getAllTvShows(any(RemoteRepository.LoadTvShowCallback.class));

		assertNotNull(result);
		assertEquals(tvShowResponses.size(), result.size());
	}

	@Test
	public void getTvShowById() {
		doAnswer(invocation -> {
			((RemoteRepository.LoadTvShowCallback) invocation.getArguments()[0])
					.onAllTvShowsReceived(tvShowResponses);
			return null;
		}).when(remoteRepository).getAllTvShows(any(RemoteRepository.LoadTvShowCallback.class));

		MovieEntity result = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId));

		verify(remoteRepository, times(1)).getAllTvShows(any(RemoteRepository.LoadTvShowCallback.class));

		MovieResponse response = tvShowResponses.get(0);

		assertNotNull(result);
		assertEquals(response.getId(), result.getId());
		assertEquals(response.getTitle(), result.getTitle());
		assertEquals(response.getDescription(), result.getDescription());
		assertEquals(response.getVoteAverage(), result.getVoteAverage());
		assertEquals(response.getReleaseDate(), result.getReleaseDate());
		assertEquals(response.getOriginalLanguage(), result.getOriginalLanguage());
		assertEquals(response.getPoster(), result.getPoster());
	}
}