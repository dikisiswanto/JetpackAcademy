package id.dikisiswanto.jetpackacademy.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static id.dikisiswanto.jetpackacademy.utils.EspressoTestsMatchers.withDrawable;

public class DetailActivityTest {

	private MovieEntity dummyEntitiy = FakeDataDummy.getMovies().get(0);
	private Context context;

	@Rule
	public ActivityTestRule<DetailActivity> activityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
		@Override
		protected Intent getActivityIntent() {
			Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
			Intent result = new Intent(targetContext, DetailActivity.class);
			result.putExtra(DetailActivity.ENTITY_ID, dummyEntitiy.getId());
			return result;
		}
	};

	@Before
	public void setUp() {
		context = activityTestRule.getActivity().getApplicationContext();
	}

	@Test
	public void loadDetails() {
		onView(withId(R.id.title)).check(matches(isDisplayed()));
		onView(withId(R.id.title)).check(matches(withText(dummyEntitiy.getTitle())));
		onView(withId(R.id.overview)).check(matches(isDisplayed()));
		onView(withId(R.id.overview)).check(matches(withText(dummyEntitiy.getDescription())));
		onView(withId(R.id.release_date)).check(matches(isDisplayed()));
		onView(withId(R.id.release_date)).check(matches(withText(dummyEntitiy.getReleaseDate())));
		onView(withId(R.id.runtime)).check(matches(isDisplayed()));
		onView(withId(R.id.runtime)).check(matches(withText(dummyEntitiy.getRuntime())));
		onView(withId(R.id.original_language)).check(matches(isDisplayed()));
		onView(withId(R.id.original_language)).check(matches(withText(dummyEntitiy.getOriginalLanguage())));
		onView(withId(R.id.poster)).check(matches(isDisplayed()));
		onView(withId(R.id.poster)).check(matches(withDrawable(context.getResources().getIdentifier(dummyEntitiy.getPoster(), "drawable", context.getPackageName()))));
		onView(withId(R.id.backdrop)).check(matches(isDisplayed()));
		onView(withId(R.id.backdrop)).check(matches(withDrawable(context.getResources().getIdentifier(dummyEntitiy.getPoster(), "drawable", context.getPackageName()))));
	}
}