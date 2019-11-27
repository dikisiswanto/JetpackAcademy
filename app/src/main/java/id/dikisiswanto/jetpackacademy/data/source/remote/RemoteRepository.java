package id.dikisiswanto.jetpackacademy.data.source.remote;

import android.os.Handler;

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

	public void getAllMovies(final LoadMoviesCallback callback) {
		EspressoIdlingResource.increment();
		Handler handler = new Handler();
		handler.postDelayed(() -> {
			callback.onAllMoviesReceived(jsonHelper.loadMovies());
			EspressoIdlingResource.decrement();
		}, SERVICE_LATENCY_IN_MILLIS);
	}

	public void getAllTvShows(LoadTvShowCallback callback) {
		EspressoIdlingResource.increment();
		Handler handler = new Handler();
		handler.postDelayed(() -> {
			callback.onAllTvShowsReceived(jsonHelper.loadTvShows());
			EspressoIdlingResource.decrement();
		}, SERVICE_LATENCY_IN_MILLIS);
	}

	public interface LoadMoviesCallback {
		void onAllMoviesReceived(List<MovieResponse> movieResponses);

		void onDataNotAvailable();
	}

	public interface LoadTvShowCallback {
		void onAllTvShowsReceived(List<MovieResponse> tvShowResponses);

		void onDataNotAvailable();
	}
}
