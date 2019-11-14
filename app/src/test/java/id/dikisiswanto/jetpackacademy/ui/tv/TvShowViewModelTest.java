package id.dikisiswanto.jetpackacademy.ui.tv;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.MovieEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TvShowViewModelTest {
	private TvShowViewModel viewModel;
	private final static int DATA_SIZE = 10;

	@Before
	public void setUp() {
		viewModel = new TvShowViewModel();
	}

	@Test
	public void getTvShows() {
		List<MovieEntity> tvShows = viewModel.getTvShows();
		assertNotNull(tvShows);
		assertEquals(DATA_SIZE, tvShows.size());
	}

}