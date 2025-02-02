package id.dikisiswanto.jetpackacademy.ui.home.tv;

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
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

	private static final String TYPE = "TV SHOW";
	private TvShowViewModel viewModel;
	private MovieRepository movieRepository = mock(MovieRepository.class);

	@Before
	public void setUp() {
		viewModel = new TvShowViewModel(movieRepository);
	}

	@Test
	public void getTvShows() {
		Resource<List<MovieEntity>> dummyTvShows = Resource.success(FakeDataDummy.getTvShows());
		MutableLiveData<Resource<List<MovieEntity>>> tvShows = new MutableLiveData<>();
		tvShows.setValue(dummyTvShows);

		when(movieRepository.getAllTvShows()).thenReturn(tvShows);

		Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);
		viewModel.setType(TYPE);
		viewModel.tvShows.observeForever(observer);
		verify(observer).onChanged(dummyTvShows);
	}

}