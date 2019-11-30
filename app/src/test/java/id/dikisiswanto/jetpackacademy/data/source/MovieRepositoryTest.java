package id.dikisiswanto.jetpackacademy.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.LocalRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;
import id.dikisiswanto.jetpackacademy.utils.InstantAppExecutors;
import id.dikisiswanto.jetpackacademy.utils.LiveDataTestUtil;
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private LocalRepository localRepository = Mockito.mock(LocalRepository.class);
	private RemoteRepository remoteRepository = Mockito.mock(RemoteRepository.class);
	private InstantAppExecutors appExecutors = Mockito.mock(InstantAppExecutors.class);
	private FakeMovieRepository movieRepository = new FakeMovieRepository(localRepository, remoteRepository, appExecutors);

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
		MutableLiveData<List<MovieEntity>> dummyEntities = new MutableLiveData<>();
		dummyEntities.setValue(FakeDataDummy.getMovies());

		when(localRepository.getAllMovies()).thenReturn(dummyEntities);

		Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(movieRepository.getAllMovies());

		verify(localRepository).getAllMovies();
		assertNotNull(result.data);
		assertEquals(movieResponses.size(), result.data.size());
	}

	@Test
	public void getMovieById() {
		MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
		dummyEntity.setValue(FakeDataDummy.getMovies().get(0));

		when(localRepository.getMovieById(movieId)).thenReturn(dummyEntity);
		Resource<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId));
		verify(localRepository).getMovieById(movieId);

		MovieResponse response = movieResponses.get(0);
		MovieEntity resultData = result.data;

		assertNotNull(result.data);
		assertNotNull(result.data.getTitle());
		assertEquals(response.getId(), resultData.getId());
		assertEquals(response.getTitle(), resultData.getTitle());
		assertEquals(response.getDescription(), resultData.getDescription());
		assertEquals(response.getVoteAverage(), resultData.getVoteAverage());
		assertEquals(response.getReleaseDate(), resultData.getReleaseDate());
		assertEquals(response.getOriginalLanguage(), resultData.getOriginalLanguage());
		assertEquals(response.getPoster(), resultData.getPoster());
	}

	@Test
	public void getAllTvShows() {
		MutableLiveData<List<MovieEntity>> dummyEntities = new MutableLiveData<>();
		dummyEntities.setValue(FakeDataDummy.getTvShows());

		when(localRepository.getAllTvShows()).thenReturn(dummyEntities);

		Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(movieRepository.getAllTvShows());

		verify(localRepository).getAllTvShows();
		assertNotNull(result.data);
		assertEquals(tvShowResponses.size(), result.data.size());
	}

	@Test
	public void getTvShowById() {
		MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
		dummyEntity.setValue(FakeDataDummy.getTvShows().get(0));

		when(localRepository.getTvShowById(tvShowId)).thenReturn(dummyEntity);
		Resource<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId));
		verify(localRepository).getTvShowById(tvShowId);

		MovieResponse response = tvShowResponses.get(0);
		MovieEntity resultData = result.data;

		assertNotNull(result.data);
		assertNotNull(result.data.getTitle());
		assertEquals(response.getId(), resultData.getId());
		assertEquals(response.getTitle(), resultData.getTitle());
		assertEquals(response.getDescription(), resultData.getDescription());
		assertEquals(response.getVoteAverage(), resultData.getVoteAverage());
		assertEquals(response.getReleaseDate(), resultData.getReleaseDate());
		assertEquals(response.getOriginalLanguage(), resultData.getOriginalLanguage());
		assertEquals(response.getPoster(), resultData.getPoster());
	}
}