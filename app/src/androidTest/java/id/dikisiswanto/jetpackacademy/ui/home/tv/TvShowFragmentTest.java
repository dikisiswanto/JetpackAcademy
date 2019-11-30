package id.dikisiswanto.jetpackacademy.ui.home.tv;

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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TvShowFragmentTest {
	@Rule
	public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, false, false);
	private MutableLiveData<Resource<List<MovieEntity>>> tvShows = new MutableLiveData<>();

	@Before
	public void setUp() {
		activityRule.launchActivity(new Intent());
		onView(withId(R.id.nav_bottom_2)).perform(click());
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
		activityRule.finishActivity();
	}

	@Test
	public void loadTvShows() {
		List<MovieEntity> expectedTvShows = FakeDataDummy.getMovies();
		tvShows.postValue(Resource.success(expectedTvShows));
		onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
		onView(withId(R.id.rv_tvshow)).check(new RecyclerViewItemCountAssertion(expectedTvShows.size()));
	}
}