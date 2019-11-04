package id.dikisiswanto.jetpackacademy.ui.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.MovieEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

	private RecyclerView rvTvShow;

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
		rvTvShow = view.findViewById(R.id.rv_movie);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			TvShowViewModel viewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
			List<MovieEntity> tvShows = viewModel.getTvShows(getContext());

			TvShowAdapter adapter = new TvShowAdapter(getActivity());
			adapter.setTvShows(tvShows);

			rvTvShow.setLayoutManager(new GridLayoutManager(getContext(), 3));
			rvTvShow.setHasFixedSize(true);
			rvTvShow.setAdapter(adapter);
		}
	}
}
