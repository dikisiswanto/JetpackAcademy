package id.dikisiswanto.jetpackacademy.ui.home.favorite.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteTvShowViewModelTest {

	private final static String type = "FAVORITE TV SHOWS";
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	private FavoriteTvShowViewModel viewModel;
	private MovieRepository movieRepository = mock(MovieRepository.class);

	@Before
	public void setUp() {
		viewModel = new FavoriteTvShowViewModel(movieRepository);
	}

	@Test
	public void getTvShowFavorites() {
		MutableLiveData<Resource<PagedList<MovieEntity>>> dummyFavoriteTvShows = new MutableLiveData<>();
		PagedList<MovieEntity> pagedList = mock(PagedList.class);

		dummyFavoriteTvShows.setValue(Resource.success(pagedList));
		when(movieRepository.getFavoriteTvShows()).thenReturn(dummyFavoriteTvShows);

		Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);
		viewModel.setType(type);
		viewModel.favoriteTvShows.observeForever(observer);
		verify(observer).onChanged(Resource.success(pagedList));
	}
}