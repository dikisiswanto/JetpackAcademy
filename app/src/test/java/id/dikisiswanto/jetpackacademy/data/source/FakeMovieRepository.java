package id.dikisiswanto.jetpackacademy.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;

public class FakeMovieRepository implements MovieDataSource {
	private volatile static FakeMovieRepository INSTANCE = null;
	private final RemoteRepository remoteRepository;

	public FakeMovieRepository(@NonNull RemoteRepository remoteRepository) {
		this.remoteRepository = remoteRepository;
	}

	public static FakeMovieRepository getInstance(RemoteRepository remoteData) {
		if (INSTANCE == null) {
			synchronized (FakeMovieRepository.class) {
				if (INSTANCE == null) {
					INSTANCE = new FakeMovieRepository(remoteData);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public LiveData<List<MovieEntity>> getAllMovies() {
		MutableLiveData<List<MovieEntity>> movieResults = new MutableLiveData<>();

		remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
			@Override
			public void onAllMoviesReceived(List<MovieResponse> movieResponses) {
				List<MovieEntity> movieList = new ArrayList<>();
				for (int i = 0; i < movieResponses.size(); i++) {
					MovieResponse response = movieResponses.get(i);
					MovieEntity movie = new MovieEntity(
							response.getId(),
							response.getTitle(),
							response.getReleaseDate(),
							response.getDescription(),
							response.getVoteAverage(),
							response.getOriginalLanguage(),
							response.getPoster()
					);
					movieList.add(movie);
				}
				movieResults.postValue(movieList);
			}

			@Override
			public void onDataNotAvailable() {

			}
		});

		return movieResults;
	}

	@Override
	public LiveData<MovieEntity> getMovieById(String movieId) {
		MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

		remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
			@Override
			public void onAllMoviesReceived(List<MovieResponse> movieResponses) {
				for (int i = 0; i < movieResponses.size(); i++) {
					MovieResponse response = movieResponses.get(i);
					if (response.getId().equals(movieId)) {
						MovieEntity movie = new MovieEntity(
								response.getId(),
								response.getTitle(),
								response.getReleaseDate(),
								response.getDescription(),
								response.getVoteAverage(),
								response.getOriginalLanguage(),
								response.getPoster()
						);
						movieResult.postValue(movie);
					}
				}
			}

			@Override
			public void onDataNotAvailable() {
				movieResult.postValue(null);
			}
		});

		return movieResult;
	}

	@Override
	public LiveData<List<MovieEntity>> getAllTvShows() {
		MutableLiveData<List<MovieEntity>> tvShowResults = new MutableLiveData<>();

		remoteRepository.getAllTvShows(new RemoteRepository.LoadTvShowCallback() {
			@Override
			public void onAllTvShowsReceived(List<MovieResponse> tvShowResponses) {
				List<MovieEntity> tvShowList = new ArrayList<>();
				for (int i = 0; i < tvShowResponses.size(); i++) {
					MovieResponse response = tvShowResponses.get(i);
					MovieEntity tvShow = new MovieEntity(
							response.getId(),
							response.getTitle(),
							response.getReleaseDate(),
							response.getDescription(),
							response.getVoteAverage(),
							response.getOriginalLanguage(),
							response.getPoster()
					);
					tvShowList.add(tvShow);
				}
				tvShowResults.postValue(tvShowList);
			}

			@Override
			public void onDataNotAvailable() {

			}
		});


		return tvShowResults;
	}

	@Override
	public LiveData<MovieEntity> getTvShowById(String tvShowId) {
		MutableLiveData<MovieEntity> tvShowResult = new MutableLiveData<>();

		remoteRepository.getAllTvShows(new RemoteRepository.LoadTvShowCallback() {
			@Override
			public void onAllTvShowsReceived(List<MovieResponse> tvShowResponses) {
				for (int i = 0; i < tvShowResponses.size(); i++) {
					MovieResponse response = tvShowResponses.get(i);
					if (response.getId().equals(tvShowId)) {
						MovieEntity tvShow = new MovieEntity(
								response.getId(),
								response.getTitle(),
								response.getReleaseDate(),
								response.getDescription(),
								response.getVoteAverage(),
								response.getOriginalLanguage(),
								response.getPoster()
						);
						tvShowResult.postValue(tvShow);
					}
				}
			}

			@Override
			public void onDataNotAvailable() {
				tvShowResult.postValue(null);
			}
		});


		return tvShowResult;
	}
}
