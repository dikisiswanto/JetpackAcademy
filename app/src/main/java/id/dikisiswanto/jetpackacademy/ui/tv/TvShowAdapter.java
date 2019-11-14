package id.dikisiswanto.jetpackacademy.ui.tv;

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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {
	private Activity activity;
	private List<MovieEntity> tvShows = new ArrayList<>();

	public TvShowAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setTvShows(List<MovieEntity> tvShows) {
		this.tvShows = tvShows;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.bind(tvShows.get(position));
	}

	@Override
	public int getItemCount() {
		return tvShows.size();
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
					details.putExtra(DetailActivity.ENTITY_ID, tvShows.get(getAdapterPosition()).getId());
					view.getContext().startActivity(details);
				}
			});
		}

		void bind(MovieEntity tvShow) {
			title.setText(tvShow.getTitle());
			poster.setImageResource(itemView.getResources().getIdentifier(tvShow.getPoster(), "drawable", itemView.getContext().getPackageName()));
		}
	}
}
