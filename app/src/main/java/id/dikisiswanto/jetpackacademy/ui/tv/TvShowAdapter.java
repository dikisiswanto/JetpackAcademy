package id.dikisiswanto.jetpackacademy.ui.tv;

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

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.ui.detail.DetailActivity;

import static id.dikisiswanto.jetpackacademy.utils.Constant.IMAGE_URL;

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
			itemView.setOnClickListener(view -> {
				Intent details = new Intent(view.getContext(), DetailActivity.class);
				Bundle extras = new Bundle();
				extras.putString(DetailActivity.ENTITY_ID, tvShows.get(getAdapterPosition()).getId());
				extras.putInt(DetailActivity.ENTITY_TYPE, 2);
				details.putExtras(extras);
				view.getContext().startActivity(details);
			});
		}

		void bind(MovieEntity tvShow) {
			title.setText(tvShow.getTitle());
			Glide.with(itemView.getContext())
					.load(IMAGE_URL + tvShow.getPoster())
					.apply(new RequestOptions())
					.into(poster);
		}
	}
}
