package id.dikisiswanto.jetpackacademy.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;

public class DetailViewModel extends ViewModel {
	private MovieRepository movieRepository;
	private LiveData<MovieEntity> entity = null;
	private String id;
	private int type;

	public DetailViewModel(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	LiveData<MovieEntity> getDetails() {
		switch (type) {
			case 1:
				entity = movieRepository.getMovieById(id);
				break;
			case 2:
				entity = movieRepository.getTvShowById(id);
				break;
		}
		return entity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
