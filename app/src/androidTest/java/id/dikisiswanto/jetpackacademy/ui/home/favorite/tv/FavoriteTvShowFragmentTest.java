package id.dikisiswanto.jetpackacademy.ui.home.favorite.tv;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.home.HomeActivity;
import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteTvShowFragmentTest {
	@Rule
	public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, false, false);

	@Before
	public void setUp() {
		activityRule.launchActivity(new Intent());
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
		onView(withId(R.id.nav_bottom_3)).perform(click());
		onView(withId(R.id.viewpager)).perform(swipeLeft());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
		activityRule.finishActivity();
	}

	@Test
	public void LoadFavoriteTvShows() {
		onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()));
	}
}