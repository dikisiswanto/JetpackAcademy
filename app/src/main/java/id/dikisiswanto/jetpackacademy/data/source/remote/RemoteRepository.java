package id.dikisiswanto.jetpackacademy.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;
import id.dikisiswanto.jetpackacademy.utils.EspressoIdlingResource;
import id.dikisiswanto.jetpackacademy.utils.JsonHelper;

public class RemoteRepository {
	private static RemoteRepository INSTANCE;
	private final long SERVICE_LATENCY_IN_MILLIS = 2000;
	private JsonHelper jsonHelper;

	public RemoteRepository(JsonHelper jsonHelper) {
		this.jsonHelper = jsonHelper;
	}

	public static RemoteRepository getInstance(JsonHelper helper) {
		if (INSTANCE == null) {
			INSTANCE = new RemoteRepository(helper);
		}
		return INSTANCE;
	}

	public LiveData<ApiResponse<List<MovieResponse>>> getAllMovies() {
		EspressoIdlingResource.increment();
		MutableLiveData<ApiResponse<List<MovieResponse>>> movieResponses = new MutableLiveData<>();

		Handler handler = new Handler();
		handler.postDelayed(() -> {
			movieResponses.setValue(ApiResponse.success(jsonHelper.loadMovies()));
			if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
				EspressoIdlingResource.decrement();
			}
		}, SERVICE_LATENCY_IN_MILLIS);

		return movieResponses;
	}

	public LiveData<ApiResponse<List<MovieResponse>>> getAllTvShows() {
		EspressoIdlingResource.increment();
		MutableLiveData<ApiResponse<List<MovieResponse>>> tvShowResponses = new MutableLiveData<>();

		Handler handler = new Handler();
		handler.postDelayed(() -> {
			tvShowResponses.setValue(ApiResponse.success(jsonHelper.loadTvShows()));
			if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
				EspressoIdlingResource.decrement();
			}
		}, SERVICE_LATENCY_IN_MILLIS);

		return tvShowResponses;
	}
}
