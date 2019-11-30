package id.dikisiswanto.jetpackacademy.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.ui.detail.DetailActivity;

import static id.dikisiswanto.jetpackacademy.utils.Constant.IMAGE_URL;

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
		@BindView(R.id.title)
		TextView title;
		@BindView(R.id.poster)
		ImageView poster;

		ViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(view -> {
				Intent details = new Intent(view.getContext(), DetailActivity.class);
				Bundle extras = new Bundle();
				extras.putString(DetailActivity.ENTITY_ID, movies.get(getAdapterPosition()).getId());
				extras.putInt(DetailActivity.ENTITY_TYPE, movies.get(getAdapterPosition()).getType());
				details.putExtras(extras);
				view.getContext().startActivity(details);
			});
		}

		void bind(MovieEntity movie) {
			title.setText(movie.getTitle());
			Glide.with(itemView.getContext())
					.load(IMAGE_URL + movie.getPoster())
					.apply(new RequestOptions())
					.placeholder(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
					.into(poster);
		}
	}
}
