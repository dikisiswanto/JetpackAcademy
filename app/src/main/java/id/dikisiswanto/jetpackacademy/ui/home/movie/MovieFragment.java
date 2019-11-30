package id.dikisiswanto.jetpackacademy.ui.home.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import id.dikisiswanto.jetpackacademy.ui.home.MovieAdapter;
import id.dikisiswanto.jetpackacademy.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

	@BindView(R.id.rv_movie)
	RecyclerView rvMovie;
	@BindView(R.id.progress)
	ProgressBar progressBar;

	public MovieFragment() {
		// Required empty public constructor
	}

	@NonNull
	private static MovieViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
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
		ButterKnife.bind(this, view);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null) {
			MovieViewModel viewModel = obtainViewModel(getActivity());
			MovieAdapter adapter = new MovieAdapter(getActivity());

			progressBar.setVisibility(View.VISIBLE);

			viewModel.setType(getString(R.string.title_tab1));
			viewModel.movies.observe(this, movies -> {
				if (movies != null) {
					switch (movies.status) {
						case LOADING:
							progressBar.setVisibility(View.VISIBLE);
							break;
						case SUCCESS:
							progressBar.setVisibility(View.GONE);
							adapter.setMovies(movies.data);
							adapter.notifyDataSetChanged();
							break;
						case ERROR:
							progressBar.setVisibility(View.GONE);
							Toast.makeText(getContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
					}
				}
			});

			rvMovie.setLayoutManager(new GridLayoutManager(getContext(), 3));
			rvMovie.setHasFixedSize(true);
			rvMovie.setAdapter(adapter);
		}
	}
}
