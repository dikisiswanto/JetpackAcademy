package id.dikisiswanto.jetpackacademy.ui.detail;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.DataDummy;

public class DetailViewModel extends ViewModel {
	private MovieEntity entity;
	private String id;

	MovieEntity getDetails() {
		List<MovieEntity> entityList = new ArrayList<>();
		entityList.addAll(DataDummy.getMovies());
		entityList.addAll(DataDummy.getTvShows());
		for (int i = 0; i < entityList.size(); i++) {
			MovieEntity mEntity = entityList.get(i);
			if (mEntity.getId().equals(id)) {
				entity = mEntity;
			}
		}
		return entity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
