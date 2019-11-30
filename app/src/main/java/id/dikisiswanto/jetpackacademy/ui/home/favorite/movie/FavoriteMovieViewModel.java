package id.dikisiswanto.jetpackacademy.ui.home.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public class FavoriteMovieViewModel extends ViewModel {
	MutableLiveData<String> type = new MutableLiveData<>();
	private MovieRepository movieRepository;
	LiveData<Resource<PagedList<MovieEntity>>> favoriteMovies = Transformations.switchMap(type, data -> movieRepository.getFavoriteMovies());

	public FavoriteMovieViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public void setType(String type) {
		this.type.setValue(type);
	}
}
