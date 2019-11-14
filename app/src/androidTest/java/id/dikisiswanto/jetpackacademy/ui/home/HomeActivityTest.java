package id.dikisiswanto.jetpackacademy.ui.home;

import android.content.Intent;

import androidx.annotation.UiThread;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class HomeActivityTest {
	@Rule
	public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);
	private HomeActivity activity;

	@Before
	public void setUp() {
		activity = activityTestRule.getActivity();
		activityTestRule.launchActivity(new Intent());
		assertThat(activityTestRule, notNullValue());
	}

	@Test
	public void swipePage() {
		onView(withId(R.id.viewpager))
				.check(matches(isDisplayed()));

		onView(withId(R.id.viewpager))
				.perform(swipeLeft());
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
		onView(allOf(withText("TV Shows"), isDescendantOfA(withId(R.id.tabs))))
				.perform(click())
				.check(matches(isDisplayed()));

		// Kemudian bandingkan judul tab
		assertThat(activity.getAdapter().getPageTitle(1), Matchers.<CharSequence>equalTo("TV Shows"));
	}
}