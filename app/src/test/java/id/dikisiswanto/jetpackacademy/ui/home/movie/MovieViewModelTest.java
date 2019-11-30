package id.dikisiswanto.jetpackacademy.ui.home.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private MovieViewModel viewModel;
	private MovieRepository movieRepository = mock(MovieRepository.class);

	@Before
	public void setUp() {
		viewModel = new MovieViewModel(movieRepository);
	}

	@Test
	public void getMovies() {
		List<MovieEntity> dummyMovies = FakeDataDummy.getMovies();
		MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
		movies.setValue(dummyMovies);

		when(movieRepository.getAllMovies()).thenReturn(movies);
		Observer<List<MovieEntity>> observer = mock(Observer.class);
		viewModel.getMovies().observeForever(observer);
		verify(observer).onChanged(dummyMovies);
	}

}