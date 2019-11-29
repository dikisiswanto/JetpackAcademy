package id.dikisiswanto.jetpackacademy.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public class MovieViewModel extends ViewModel {

	private MutableLiveData<String> result = new MutableLiveData<>();
	private MovieRepository movieRepository;

	public MovieViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	LiveData<Resource<List<MovieEntity>>> movies = Transformations.switchMap(result, data ->
			movieRepository.getAllMovies());

	public void setType(String type) {
		result.setValue(type);
	}
}
