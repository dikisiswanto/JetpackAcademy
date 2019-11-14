package id.dikisiswanto.jetpackacademy.ui.tv;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.DataDummy;

public class TvShowViewModel extends ViewModel {

	public List<MovieEntity> getTvShows() {
		return DataDummy.getTvShows();
	}
}
