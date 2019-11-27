package id.dikisiswanto.jetpackacademy.ui.tv;


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

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
	private RecyclerView rvTvShow;
	private ProgressBar progressBar;

	public TvShowFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_tv_show, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvTvShow = view.findViewById(R.id.rv_tvshow);
		progressBar = view.findViewById(R.id.progress);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			TvShowViewModel viewModel = obtainViewModel(getActivity());
			TvShowAdapter adapter = new TvShowAdapter(getActivity());

			progressBar.setVisibility(View.VISIBLE);

			viewModel.getTvShows().observe(this, tvshows -> {
				progressBar.setVisibility(View.GONE);
				adapter.setTvShows(tvshows);
				adapter.notifyDataSetChanged();
			});

			rvTvShow.setLayoutManager(new GridLayoutManager(getContext(), 3));
			rvTvShow.setHasFixedSize(true);
			rvTvShow.setAdapter(adapter);
		}
	}

	@NonNull
	private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
	}
}
