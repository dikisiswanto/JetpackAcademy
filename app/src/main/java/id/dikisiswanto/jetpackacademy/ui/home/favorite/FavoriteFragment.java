package id.dikisiswanto.jetpackacademy.ui.home.favorite;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.home.favorite.movie.FavoriteMovieFragment;
import id.dikisiswanto.jetpackacademy.ui.home.favorite.tv.FavoriteTvShowFragment;
import id.dikisiswanto.jetpackacademy.utils.ViewPagerAdapter;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

	@BindView(R.id.viewpager)
	ViewPager viewPager;
	@BindView(R.id.tabs)
	TabLayout tabLayout;
	private ViewPagerAdapter adapter;


	public FavoriteFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_favorite, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		setupViewPager(viewPager);
		tabLayout.setupWithViewPager(viewPager);
	}

	private void setupViewPager(ViewPager viewPager) {
		adapter = new ViewPagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		adapter.addFragment(new FavoriteMovieFragment(), getString(R.string.favorite_movie));
		adapter.addFragment(new FavoriteTvShowFragment(), getString(R.string.favorite_tv_show));
		viewPager.setAdapter(adapter);
	}

	public ViewPagerAdapter getAdapter() {
		return adapter;
	}
}
