package id.dikisiswanto.jetpackacademy.ui.movie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.MovieEntity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

	private Activity activity;
	private List<MovieEntity> movies = new ArrayList<>();

	public MovieAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.bind(movies.get(position));
	}

	@Override
	public int getItemCount() {
		return movies.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		final TextView title;
		final ImageView poster;

		ViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.title);
			poster = itemView.findViewById(R.id.poster);
		}

		void bind(MovieEntity movie) {
			title.setText(movie.getTitle());
			poster.setImageResource(movie.getPoster());
		}
	}
}
