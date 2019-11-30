package id.dikisiswanto.jetpackacademy.ui.home.favorite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.ui.detail.DetailActivity;

import static id.dikisiswanto.jetpackacademy.utils.Constant.IMAGE_URL;

public class FavoriteAdapter extends PagedListAdapter<MovieEntity, FavoriteAdapter.FavoriteViewHolder> {

	private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
			new DiffUtil.ItemCallback<MovieEntity>() {
				@Override
				public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
					return oldItem.getId().equals(newItem.getId());
				}

				@SuppressLint("DiffUtilEquals")
				@Override
				public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
					return oldItem.equals(newItem);
				}
			};
	private FavoriteFragmentCallback callback;

	public FavoriteAdapter(FavoriteFragmentCallback callback) {
		super(DIFF_CALLBACK);
		this.callback = callback;
	}

	@NonNull
	@Override
	public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
		return new FavoriteViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
		holder.bind(getItem(position));
	}

	MovieEntity getItemById(int position) {
		return getItem(position);
	}

	public class FavoriteViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.title)
		TextView title;
		@BindView(R.id.poster)
		ImageView poster;

		public FavoriteViewHolder(@NonNull View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(view -> {
				Intent details = new Intent(view.getContext(), DetailActivity.class);
				Bundle extras = new Bundle();
				extras.putString(DetailActivity.ENTITY_ID, getItem(getAdapterPosition()).getId());
				extras.putInt(DetailActivity.ENTITY_TYPE, getItem(getAdapterPosition()).getType());
				details.putExtras(extras);
				view.getContext().startActivity(details);
			});
		}

		public void bind(MovieEntity item) {
			title.setText(item.getTitle());
			Glide.with(itemView.getContext())
					.load(IMAGE_URL + item.getPoster())
					.apply(new RequestOptions())
					.placeholder(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
					.into(poster);
		}
	}
}
