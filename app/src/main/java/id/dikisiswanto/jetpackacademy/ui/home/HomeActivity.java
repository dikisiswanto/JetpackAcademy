package id.dikisiswanto.jetpackacademy.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.movie.MovieFragment;
import id.dikisiswanto.jetpackacademy.ui.tv.TvShowFragment;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class HomeActivity extends AppCompatActivity {

	private ViewPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ViewPager viewPager = findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		TabLayout tabLayout = findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
	}

	private void setupViewPager(ViewPager viewPager) {
		adapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		adapter.addFragment(new MovieFragment(), "Movies");
		adapter.addFragment(new TvShowFragment(), "TV Shows");
		viewPager.setAdapter(adapter);
	}

	public ViewPagerAdapter getAdapter() {
		return adapter;
	}
}
