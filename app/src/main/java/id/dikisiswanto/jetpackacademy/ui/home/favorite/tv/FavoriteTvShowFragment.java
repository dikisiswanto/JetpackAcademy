package id.dikisiswanto.jetpackacademy.ui.home.favorite.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.ui.home.favorite.FavoriteAdapter;
import id.dikisiswanto.jetpackacademy.ui.home.favorite.FavoriteFragmentCallback;
import id.dikisiswanto.jetpackacademy.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment implements FavoriteFragmentCallback {

	@BindView(R.id.rv_tvshow)
	RecyclerView rvTvShow;
	@BindView(R.id.progress)
	ProgressBar progressBar;

	public FavoriteTvShowFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			FavoriteTvShowViewModel viewModel = obtainViewModel(getActivity());
			FavoriteAdapter adapter = new FavoriteAdapter(this);

			progressBar.setVisibility(View.VISIBLE);

			viewModel.setType(getString(R.string.title_tab2));
			viewModel.favoriteTvShows.observe(this, tvShows -> {
				if (tvShows.data != null) {
					progressBar.setVisibility(View.GONE);
					adapter.submitList(tvShows.data);
					adapter.notifyDataSetChanged();
				}
			});

			rvTvShow.setLayoutManager(new GridLayoutManager(getContext(), 3));
			rvTvShow.setHasFixedSize(true);
			rvTvShow.setAdapter(adapter);
		}
	}

	private FavoriteTvShowViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(FavoriteTvShowViewModel.class);
	}
}
