package id.dikisiswanto.jetpackacademy.ui.movie;

import android.app.Activity;
import android.content.Intent;
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
import id.dikisiswanto.jetpackacademy.ui.detail.DetailActivity;

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
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent details = new Intent(view.getContext(), DetailActivity.class);
					details.putExtra(DetailActivity.ENTITY_ID, movies.get(getAdapterPosition()).getId());
					view.getContext().startActivity(details);
				}
			});
		}

		void bind(MovieEntity movie) {
			title.setText(movie.getTitle());
			poster.setImageResource(movie.getPoster());
		}
	}
}
