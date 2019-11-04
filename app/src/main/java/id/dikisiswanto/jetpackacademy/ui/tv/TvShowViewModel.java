package id.dikisiswanto.jetpackacademy.ui.tv;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.MovieEntity;
import id.dikisiswanto.jetpackacademy.utils.DataDummy;

public class TvShowViewModel extends ViewModel {
	public List<MovieEntity> getTvShows(Context context) {
		return DataDummy.getTvShows(context);
	}
}
