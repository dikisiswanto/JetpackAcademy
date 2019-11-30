package id.dikisiswanto.jetpackacademy.ui.home.favorite;

import androidx.annotation.UiThread;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.home.HomeActivity;
import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;

public class FavoriteFragmentTest {
	@Rule
	public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);
	private HomeActivity activity;
	private FavoriteFragment fragment = new FavoriteFragment();

	@Before
	public void setUp() {
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
		activity = activityTestRule.getActivity();
//		activityTestRule.launchActivity(new Intent());
		onView(withId(R.id.nav_bottom_3)).perform(click());
	}

	@Test
	public void swipePage() {
		onView(withId(R.id.viewpager))
				.check(matches(isDisplayed()));

		onView(withId(R.id.viewpager))
				.perform(swipeLeft());

		onView(withId(R.id.viewpager))
				.perform(swipeRight());
	}

	@Test
	public void checkTabLayoutDisplayed() {
		onView(withId(R.id.tabs))
				.perform(click())
				.check(matches(isDisplayed()));
	}

	@Test
	@UiThread
	public void checkTabSwitch() {
		// Pindah ke tab Tv Shows
		onView(allOf(withText(String.valueOf(withId(R.string.favorite_tv_show))), isDescendantOfA(withId(R.id.tabs))))
				.perform(click())
				.check(matches(isDisplayed()));

		// Kemudian bandingkan judul tab
		assertThat(fragment.getAdapter().getPageTitle(1), Matchers.equalTo(withId(R.string.favorite_tv_show)));
	}

}