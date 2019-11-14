package id.dikisiswanto.jetpackacademy.ui.movie;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.DataDummy;

public class MovieViewModel extends ViewModel {

	public List<MovieEntity> getMovies() {
		return DataDummy.getMovies();
	}
}
