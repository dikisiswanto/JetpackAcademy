package id.dikisiswanto.jetpackacademy.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.vo.Resource;

import static id.dikisiswanto.jetpackacademy.utils.Constant.MOVIE_TYPE;
import static id.dikisiswanto.jetpackacademy.utils.Constant.TV_SHOW_TYPE;

public class DetailViewModel extends ViewModel {
	private MovieRepository movieRepository;
	private MutableLiveData<String> id = new MutableLiveData<>();
	private LiveData<Resource<MovieEntity>> result;
	private int type;

	public DetailViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	LiveData<Resource<MovieEntity>> detail = Transformations.switchMap(id, data -> {
		switch (type) {
			case MOVIE_TYPE:
				result = movieRepository.getMovieById(getId());
				break;
			case TV_SHOW_TYPE:
				result = movieRepository.getTvShowById(getId());
		}
		return result;
	});

	public String getId() {
		return this.id.getValue();
	}

	public void setId(String id) {
		this.id.setValue(id);
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setFavorite() {
		if (detail.getValue() != null) {
			MovieEntity entity = detail.getValue().data;
			movieRepository.setFavoriteStatus(entity);
		}
	}
}
