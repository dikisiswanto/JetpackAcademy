package id.dikisiswanto.jetpackacademy.ui.movie;


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
public class MovieFragment extends Fragment {

	private RecyclerView rvMovie;

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
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
			List<MovieEntity> movies = viewModel.getMovies(getContext());

			MovieAdapter adapter = new MovieAdapter(getActivity());
			adapter.setMovies(movies);

			rvMovie.setLayoutManager(new GridLayoutManager(getActivity(), 3));
			rvMovie.setHasFixedSize(true);
			rvMovie.setAdapter(adapter);
		}
	}
}
