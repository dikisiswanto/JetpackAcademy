package id.dikisiswanto.jetpackacademy.ui.home.movie;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.ui.home.HomeActivity;
import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;
import id.dikisiswanto.jetpackacademy.utils.RecyclerViewItemCountAssertion;
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieFragmentTest {

	@Rule
	public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, false, false);
	private MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();

	@Before
	public void setUp() {
		activityRule.launchActivity(new Intent());
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
	}

	@Test
	public void loadMovies() {
		List<MovieEntity> expectedMovies = FakeDataDummy.getMovies();
		movies.postValue(Resource.success(expectedMovies));
		onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
		onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(expectedMovies.size()));
	}
}