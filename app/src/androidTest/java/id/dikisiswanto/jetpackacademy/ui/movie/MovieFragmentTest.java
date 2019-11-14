package id.dikisiswanto.jetpackacademy.ui.movie;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.home.HomeActivity;
import id.dikisiswanto.jetpackacademy.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieFragmentTest {

	@Rule
	public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, false, false);

	@Before
	public void setUp() {
		activityRule.launchActivity(new Intent());
	}

	@Test
	public void loadMovies() {
		onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
		onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(10));
	}
}