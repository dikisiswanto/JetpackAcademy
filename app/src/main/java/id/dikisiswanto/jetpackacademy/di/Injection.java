package id.dikisiswanto.jetpackacademy.di;

import android.app.Application;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.utils.JsonHelper;

public class Injection {
	public static MovieRepository provideRepository(Application application) {
		RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
		return MovieRepository.getInstance(remoteRepository);
	}
}
