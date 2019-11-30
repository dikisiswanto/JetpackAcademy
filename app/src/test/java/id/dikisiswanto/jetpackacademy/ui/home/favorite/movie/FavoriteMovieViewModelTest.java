package id.dikisiswanto.jetpackacademy.ui.home.favorite.movie;

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

public class FavoriteMovieViewModelTest {
	private final static String type = "FAVORITE MOVIES";
	@Rule
	public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	private FavoriteMovieViewModel viewModel;
	private MovieRepository movieRepository = mock(MovieRepository.class);

	@Before
	public void setUp() {
		viewModel = new FavoriteMovieViewModel(movieRepository);
	}

	@Test
	public void getFavoriteMovies() {
		MutableLiveData<Resource<PagedList<MovieEntity>>> dummyFavoriteMovies = new MutableLiveData<>();
		PagedList<MovieEntity> pagedList = mock(PagedList.class);

		dummyFavoriteMovies.setValue(Resource.success(pagedList));
		when(movieRepository.getFavoriteMovies()).thenReturn(dummyFavoriteMovies);

		Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);
		viewModel.setType(type);
		viewModel.favoriteMovies.observeForever(observer);
		verify(observer).onChanged(Resource.success(pagedList));
	}

}