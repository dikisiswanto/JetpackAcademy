package id.dikisiswanto.jetpackacademy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.di.Injection;
import id.dikisiswanto.jetpackacademy.ui.detail.DetailViewModel;
import id.dikisiswanto.jetpackacademy.ui.favorite.movie.FavoriteMovieViewModel;
import id.dikisiswanto.jetpackacademy.ui.favorite.tv.FavoriteTvShowViewModel;
import id.dikisiswanto.jetpackacademy.ui.movie.MovieViewModel;
import id.dikisiswanto.jetpackacademy.ui.tv.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
	private static volatile ViewModelFactory INSTANCE;

	private final MovieRepository movieRepository;

	public ViewModelFactory(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public static ViewModelFactory getInstance(Application application) {
		if (INSTANCE == null) {
			synchronized (ViewModelFactory.class) {
				if (INSTANCE == null) {
					INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
				}
			}
		}
		return INSTANCE;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

		if (modelClass.isAssignableFrom(MovieViewModel.class)) {
			//noinspection unchecked
			return (T) new MovieViewModel(movieRepository);
		} else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
			//noinspection unchecked
			return (T) new TvShowViewModel(movieRepository);
		} else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
			//noinspection unchecked
			return (T) new DetailViewModel(movieRepository);
		} else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)) {
			//noinspection unchecked
			return (T) new FavoriteMovieViewModel(movieRepository);
		} else if (modelClass.isAssignableFrom(FavoriteTvShowViewModel.class)) {
			//noinspection unchecked
			return (T) new FavoriteTvShowViewModel(movieRepository);
		}

		throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
	}
}
