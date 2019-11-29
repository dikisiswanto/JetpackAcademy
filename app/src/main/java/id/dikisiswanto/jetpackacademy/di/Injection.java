package id.dikisiswanto.jetpackacademy.di;

import android.app.Application;

import id.dikisiswanto.jetpackacademy.data.source.MovieRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.LocalRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.room.MovieDatabase;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.utils.AppExecutors;
import id.dikisiswanto.jetpackacademy.utils.JsonHelper;

public class Injection {
	public static MovieRepository provideRepository(Application application) {
		MovieDatabase database = MovieDatabase.getInstance(application);

		LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
		RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
		AppExecutors appExecutors = new AppExecutors();

		return MovieRepository.getInstance(localRepository, remoteRepository, appExecutors);
	}
}
