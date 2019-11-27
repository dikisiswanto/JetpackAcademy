package id.dikisiswanto.jetpackacademy.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailActivityTest {

	private MovieEntity dummyEntity = FakeDataDummy.getMovies().get(0);
	private static final int MOVIE_TYPE = 1;

	@Rule
	public ActivityTestRule<DetailActivity> activityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
		@Override
		protected Intent getActivityIntent() {
			Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
			Intent result = new Intent(targetContext, DetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(DetailActivity.ENTITY_ID, dummyEntity.getId());
			bundle.putInt(DetailActivity.ENTITY_TYPE, MOVIE_TYPE);
			result.putExtras(bundle);
			return result;
		}
	};

	@Before
	public void setUp() {
		Context context = activityTestRule.getActivity().getApplicationContext();
		IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
	}

	@After
	public void tearDown() {
		IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
	}

	@Test
	public void loadDetails() {
		onView(withId(R.id.title)).check(matches(isDisplayed()));
		onView(withId(R.id.title)).check(matches(withText(dummyEntity.getTitle())));
		onView(withId(R.id.overview)).check(matches(isDisplayed()));
		onView(withId(R.id.overview)).check(matches(withText(dummyEntity.getDescription())));
		onView(withId(R.id.release_date)).check(matches(isDisplayed()));
		onView(withId(R.id.release_date)).check(matches(withText(dummyEntity.getReleaseDate())));
		onView(withId(R.id.vote_average)).check(matches(isDisplayed()));
		onView(withId(R.id.vote_average)).check(matches(withText(dummyEntity.getVoteAverage())));
		onView(withId(R.id.original_language)).check(matches(isDisplayed()));
		onView(withId(R.id.original_language)).check(matches(withText(dummyEntity.getOriginalLanguage())));
		onView(withId(R.id.poster)).check(matches(isDisplayed()));
		onView(withId(R.id.backdrop)).check(matches(isDisplayed()));
	}
}