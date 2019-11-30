package id.dikisiswanto.jetpackacademy.ui.home.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public class TvShowViewModel extends ViewModel {
	private MutableLiveData<String> result = new MutableLiveData<>();
	private MovieRepository movieRepository;

	public TvShowViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	LiveData<Resource<List<MovieEntity>>> tvShows = Transformations.switchMap(result, data ->
			movieRepository.getAllTvShows());

	public void setType(String type) {
		result.setValue(type);
	}
}
