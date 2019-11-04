package id.dikisiswanto.jetpackacademy.utils;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.MovieEntity;

public class DataDummy {
	private static String[] title;
	private static String[] releaseDate;
	private static String[] description;
	private static String[] runtime;
	private static String[] originalLanguage;
	private static TypedArray poster;

	public static List<MovieEntity> getMovies(Context context) {
		List<MovieEntity> movies = new ArrayList<>();
		MovieEntity movie;
		initMovies(context);
		for (int i = 0; i < title.length; i++) {
			movie = new MovieEntity();
			movie.setTitle(title[i]);
			movie.setDescription(description[i]);
			movie.setReleaseDate(releaseDate[i]);
			movie.setRuntime(runtime[i]);
			movie.setOriginalLanguage(originalLanguage[i]);
			movie.setPoster(poster.getResourceId(i, -1));
			movies.add(movie);
		}
		return movies;
	}

	public static List<MovieEntity> getTvShows(Context context) {
		List<MovieEntity> tvShows = new ArrayList<>();
		MovieEntity tvShow;
		initTvShow(context);
		for (int i = 0; i < title.length; i++) {
			tvShow = new MovieEntity();
			tvShow.setTitle(title[i]);
			tvShow.setDescription(description[i]);
			tvShow.setReleaseDate(releaseDate[i]);
			tvShow.setRuntime(runtime[i]);
			tvShow.setOriginalLanguage(originalLanguage[i]);
			tvShow.setPoster(poster.getResourceId(i, -1));
			tvShows.add(tvShow);
		}
		return tvShows;
	}

	private static void initMovies(Context context) {
		title = context.getResources().getStringArray(R.array.title);
		description = context.getResources().getStringArray(R.array.description);
		releaseDate = context.getResources().getStringArray(R.array.release_date);
		runtime = context.getResources().getStringArray(R.array.runtime);
		originalLanguage = context.getResources().getStringArray(R.array.original_language);
		poster = context.getResources().obtainTypedArray(R.array.poster);
	}

	private static void initTvShow(Context context) {
		title = context.getResources().getStringArray(R.array.tvs_title);
		description = context.getResources().getStringArray(R.array.tvs_description);
		releaseDate = context.getResources().getStringArray(R.array.tvs_release_date);
		runtime = context.getResources().getStringArray(R.array.tvs_runtime);
		originalLanguage = context.getResources().getStringArray(R.array.tvs_original_language);
		poster = context.getResources().obtainTypedArray(R.array.tvs_poster);
	}
}
