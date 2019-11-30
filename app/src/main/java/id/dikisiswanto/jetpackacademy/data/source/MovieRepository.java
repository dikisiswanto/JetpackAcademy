package id.dikisiswanto.jetpackacademy.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.local.LocalRepository;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.data.source.remote.ApiResponse;
import id.dikisiswanto.jetpackacademy.data.source.remote.RemoteRepository;
import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;
import id.dikisiswanto.jetpackacademy.utils.AppExecutors;
import id.dikisiswanto.jetpackacademy.vo.Resource;

public class MovieRepository implements MovieDataSource {
	private volatile static MovieRepository INSTANCE = null;

	private final LocalRepository localRepository;
	private final RemoteRepository remoteRepository;
	private final AppExecutors appExecutors;

	public MovieRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, @NonNull AppExecutors appExecutors) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.appExecutors = appExecutors;
	}

	public static MovieRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
		if (INSTANCE == null) {
			synchronized (MovieRepository.class) {
				if (INSTANCE == null) {
					INSTANCE = new MovieRepository(localRepository, remoteData, appExecutors);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public LiveData<Resource<List<MovieEntity>>> getAllMovies() {

		return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
			@Override
			protected LiveData<List<MovieEntity>> loadFromDB() {
				return localRepository.getAllMovies();
			}

			@Override
			protected Boolean shouldFetch(List<MovieEntity> data) {
				return (data == null) || (data.size() == 0);
			}

			@Override
			protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
				return remoteRepository.getAllMovies();
			}

			@Override
			protected void saveCallResult(List<MovieResponse> data) {
				List<MovieEntity> movieEntities = new ArrayList<>();

				for (MovieResponse movieResponse : data) {
					movieEntities.add(new MovieEntity(
							movieResponse.getId(),
							movieResponse.getTitle(),
							movieResponse.getReleaseDate(),
							movieResponse.getDescription(),
							movieResponse.getVoteAverage(),
							movieResponse.getOriginalLanguage(),
							movieResponse.getPoster(),
							movieResponse.getType()
					));
					localRepository.insertMovies(movieEntities);
				}
			}
		}.asLiveData();

	}

	@Override
	public LiveData<Resource<MovieEntity>> getMovieById(String movieId) {
		return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

			@Override
			protected LiveData<MovieEntity> loadFromDB() {
				return localRepository.getMovieById(movieId);
			}

			@Override
			protected Boolean shouldFetch(MovieEntity data) {
				return data == null;
			}

			@Override
			protected LiveData<ApiResponse<MovieResponse>> createCall() {
				return null;
			}

			@Override
			protected void saveCallResult(MovieResponse data) {

			}
		}.asLiveData();
	}

	@Override
	public LiveData<Resource<List<MovieEntity>>> getAllTvShows() {
		return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
			@Override
			protected LiveData<List<MovieEntity>> loadFromDB() {
				return localRepository.getAllTvShows();
			}

			@Override
			protected Boolean shouldFetch(List<MovieEntity> data) {
				return (data == null) || (data.size() == 0);
			}

			@Override
			protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
				return remoteRepository.getAllTvShows();
			}

			@Override
			protected void saveCallResult(List<MovieResponse> data) {
				List<MovieEntity> tvShowEntities = new ArrayList<>();

				for (MovieResponse tvShowResponse : data) {
					tvShowEntities.add(new MovieEntity(
							tvShowResponse.getId(),
							tvShowResponse.getTitle(),
							tvShowResponse.getReleaseDate(),
							tvShowResponse.getDescription(),
							tvShowResponse.getVoteAverage(),
							tvShowResponse.getOriginalLanguage(),
							tvShowResponse.getPoster(),
							tvShowResponse.getType()
					));
					localRepository.insertMovies(tvShowEntities);
				}
			}
		}.asLiveData();
	}

	@Override
	public LiveData<Resource<MovieEntity>> getTvShowById(String tvShowId) {
		return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

			@Override
			protected LiveData<MovieEntity> loadFromDB() {
				return localRepository.getTvShowById(tvShowId);
			}

			@Override
			protected Boolean shouldFetch(MovieEntity data) {
				return data == null;
			}

			@Override
			protected LiveData<ApiResponse<MovieResponse>> createCall() {
				return null;
			}

			@Override
			protected void saveCallResult(MovieResponse data) {

			}
		}.asLiveData();
	}

	@Override
	public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies() {
		return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

			@Override
			protected LiveData<PagedList<MovieEntity>> loadFromDB() {
				return new LivePagedListBuilder<>(localRepository.getFavoriteMovies(), 20).build();
			}

			@Override
			protected Boolean shouldFetch(PagedList<MovieEntity> data) {
				return false;
			}

			@Override
			protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
				return null;
			}

			@Override
			protected void saveCallResult(List<MovieResponse> data) {

			}
		}.asLiveData();
	}

	@Override
	public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteTvShows() {
		return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

			@Override
			protected LiveData<PagedList<MovieEntity>> loadFromDB() {
				return new LivePagedListBuilder<>(localRepository.getFavoriteTvShows(), 20).build();
			}

			@Override
			protected Boolean shouldFetch(PagedList<MovieEntity> data) {
				return false;
			}

			@Override
			protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
				return null;
			}

			@Override
			protected void saveCallResult(List<MovieResponse> data) {

			}
		}.asLiveData();
	}

	@Override
	public void setFavoriteStatus(MovieEntity movieEntity) {
		boolean status = !movieEntity.getStatus();
		movieEntity.setStatus(status);
		Runnable runnable = () -> localRepository.updateFavoriteStatus(movieEntity);
		appExecutors.diskIO().execute(runnable);
	}


}
