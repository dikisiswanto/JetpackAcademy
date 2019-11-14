package id.dikisiswanto.jetpackacademy.ui.detail;

import org.junit.Before;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.data.MovieEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailViewModelTest {
	private MovieEntity dummyEntity;
	private DetailViewModel viewModel;

	@Before
	public void setUp() {
		viewModel = new DetailViewModel();
		dummyEntity = new MovieEntity("m06", "Spider-Man: Into the Spider-Verse", "December 6, 2018", "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.", "1h 57m", "English", "@drawable/poster_spiderman");
	}

	@Test
	public void getDetails() {
		viewModel.setId(dummyEntity.getId());
		MovieEntity movieEntity = viewModel.getDetails();
		assertNotNull(movieEntity);
		assertEquals(dummyEntity.getId(), movieEntity.getId());
		assertEquals(dummyEntity.getTitle(), movieEntity.getTitle());
		assertEquals(dummyEntity.getDescription(), movieEntity.getDescription());
		assertEquals(dummyEntity.getReleaseDate(), movieEntity.getReleaseDate());
		assertEquals(dummyEntity.getRuntime(), movieEntity.getRuntime());
		assertEquals(dummyEntity.getOriginalLanguage(), movieEntity.getOriginalLanguage());
		assertEquals(dummyEntity.getPoster(), movieEntity.getPoster());
	}
}