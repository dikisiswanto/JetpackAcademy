package id.dikisiswanto.jetpackacademy.ui.movie;


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
public class MovieFragment extends Fragment {

	private RecyclerView rvMovie;
	private ProgressBar progressBar;

	public MovieFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_movie, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		rvMovie = view.findViewById(R.id.rv_movie);
		progressBar = view.findViewById(R.id.progress);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			MovieViewModel viewModel = obtainViewModel(getActivity());
			MovieAdapter adapter = new MovieAdapter(getActivity());

			progressBar.setVisibility(View.VISIBLE);

			viewModel.getMovies().observe(this, movies -> {
				progressBar.setVisibility(View.GONE);
				adapter.setMovies(movies);
				adapter.notifyDataSetChanged();
			});

			rvMovie.setLayoutManager(new GridLayoutManager(getContext(), 3));
			rvMovie.setHasFixedSize(true);
			rvMovie.setAdapter(adapter);
		}
	}

	@NonNull
	private static MovieViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
	}
}
