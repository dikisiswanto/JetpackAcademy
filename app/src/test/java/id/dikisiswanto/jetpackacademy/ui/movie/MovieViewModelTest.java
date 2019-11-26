package id.dikisiswanto.jetpackacademy.ui.movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieViewModelTest {
	private MovieViewModel viewModel;
	private final static int DATA_SIZE = 10;

	@Before
	public void setUp() {
		viewModel = new MovieViewModel();
	}

	@Test
	public void getMovies() {
		List<MovieEntity> movieEntities = viewModel.getMovies();
		assertNotNull(movieEntities);
		assertEquals(DATA_SIZE, movieEntities.size());
	}

}