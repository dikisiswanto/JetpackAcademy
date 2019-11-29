package id.dikisiswanto.jetpackacademy.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.favorite.FavoriteFragment;
import id.dikisiswanto.jetpackacademy.ui.movie.MovieFragment;
import id.dikisiswanto.jetpackacademy.ui.tv.TvShowFragment;

public class HomeActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.navigation)
	BottomNavigationView navigation;
	private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = menuItem -> {
		Fragment fragment;
		switch (menuItem.getItemId()) {
			case R.id.nav_bottom_2:
				fragment = new TvShowFragment();
				break;
			case R.id.nav_bottom_3:
				fragment = new FavoriteFragment();
				break;
			default:
				fragment = new MovieFragment();
		}
		loadFragment(fragment);
		return true;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
		if (savedInstanceState == null) {
			navigation.setSelectedItemId(R.id.nav_bottom_1);
		}
	}

	private void loadFragment(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, fragment);
		transaction.commit();
	}
}
