package id.dikisiswanto.jetpackacademy.ui.home;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class HomeActivityTest {
	@Rule
	public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);
	private HomeActivity activity;

	@Before
	public void setUp() {
		activity = activityTestRule.getActivity();
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
		activityTestRule.launchActivity(new Intent());
	}

	@Test
	public void activityTest() {
		assertThat(activityTestRule, notNullValue());
	}
}