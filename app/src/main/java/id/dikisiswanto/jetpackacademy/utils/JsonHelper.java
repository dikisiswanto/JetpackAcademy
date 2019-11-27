package id.dikisiswanto.jetpackacademy.utils;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.data.source.remote.response.MovieResponse;

public class JsonHelper {
	private Application application;

	public JsonHelper(Application application) {
		this.application = application;
	}

	private String parsingFileToString(String filename) {
		try {
			InputStream is = application.getAssets().open(filename);
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			return new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<MovieResponse> loadMovies() {
		ArrayList<MovieResponse> list = new ArrayList<>();

		try {
			JSONObject responseObject = new JSONObject(parsingFileToString("movie_response.json"));
			JSONArray listArray = responseObject.getJSONArray("movies");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject movie = listArray.getJSONObject(i);

				String id = movie.getString("id");
				String title = movie.getString("title");
				String releaseDate = movie.getString("release_date");
				String description = movie.getString("overview");
				String voteAverage = movie.getString("vote_average");
				String originalLanguage = movie.getString("original_language");
				String poster = movie.getString("poster_path");

				MovieResponse movieResponse = new MovieResponse(id, title, releaseDate, description, voteAverage, originalLanguage, poster);
				list.add(movieResponse);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<MovieResponse> loadTvShows() {
		ArrayList<MovieResponse> list = new ArrayList<>();

		try {
			JSONObject responseObject = new JSONObject(parsingFileToString("tvshow_response.json"));
			JSONArray listArray = responseObject.getJSONArray("tvshows");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject tvShow = listArray.getJSONObject(i);

				String id = tvShow.getString("id");
				String title = tvShow.getString("name");
				String releaseDate = tvShow.getString("first_air_date");
				String description = tvShow.getString("overview");
				String voteAverage = tvShow.getString("vote_average");
				String originalLanguage = tvShow.getString("original_language");
				String poster = tvShow.getString("poster_path");

				MovieResponse tvShowResponse = new MovieResponse(id, title, releaseDate, description, voteAverage, originalLanguage, poster);
				list.add(tvShowResponse);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
