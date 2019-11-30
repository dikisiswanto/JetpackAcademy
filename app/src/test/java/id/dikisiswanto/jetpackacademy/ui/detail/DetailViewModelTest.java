package id.dikisiswanto.jetpackacademy.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static id.dikisiswanto.jetpackacademy.utils.Constant.MOVIE_TYPE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private DetailViewModel viewModel;
	private MovieRepository movieRepository = mock(MovieRepository.class);
	private Resource<MovieEntity> dummyMovie = Resource.success(FakeDataDummy.getMovies().get(0));
	private String movieId = dummyMovie.data.getId();

	@Before
	public void setUp() {
		viewModel = new DetailViewModel(movieRepository);
		viewModel.setId(movieId);
		viewModel.setType(MOVIE_TYPE);
	}

	@Test
	public void getDetails() {
		MutableLiveData<Resource<MovieEntity>> movieEntity = new MutableLiveData<>();
		movieEntity.setValue(dummyMovie);

		when(movieRepository.getMovieById(movieId)).thenReturn(movieEntity);

		Observer<Resource<MovieEntity>> observer = mock(Observer.class);
		viewModel.detail.observeForever(observer);
		verify(observer).onChanged(dummyMovie);
	}
}