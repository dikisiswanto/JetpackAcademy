package id.dikisiswanto.jetpackacademy.ui.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.viewmodel.ViewModelFactory;

import static id.dikisiswanto.jetpackacademy.utils.Constant.IMAGE_URL;

public class DetailActivity extends AppCompatActivity {

	public static final String ENTITY_ID = "entity_id";
	public static final String ENTITY_TYPE = "entity_type";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		ProgressBar progressBar = findViewById(R.id.progress);
		LinearLayout content = findViewById(R.id.content);
		Toolbar toolbar = findViewById(R.id.toolbar_title);

		toolbar.setTitle("");
		setSupportActionBar(toolbar);

		progressBar.setVisibility(View.VISIBLE);
		content.setVisibility(View.INVISIBLE);

		DetailViewModel viewModel = obtainViewModel(this);
		Bundle extra = getIntent().getExtras();
		if (extra != null) {
			String entityId = extra.getString(ENTITY_ID);
			int entityType = extra.getInt(ENTITY_TYPE);
			if (entityId != null) {
				viewModel.setId(entityId);
				viewModel.setType(entityType);
			} else {
				finish();
			}
		}
		viewModel.getDetails().observe(this, entity -> {
			if (entity != null) {
				progressBar.setVisibility(View.GONE);
				content.setVisibility(View.VISIBLE);
				populateDetails(entity);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void populateDetails(MovieEntity entity) {
		TextView title = findViewById(R.id.title);
		TextView description = findViewById(R.id.overview);
		TextView originalLanguage = findViewById(R.id.original_language);
		TextView releaseDate = findViewById(R.id.release_date);
		TextView voteAvg = findViewById(R.id.vote_average);
		ImageView poster = findViewById(R.id.poster);
		ImageView backdrop = findViewById(R.id.backdrop);

		title.setText(entity.getTitle());
		description.setText(entity.getDescription());
		originalLanguage.setText(entity.getOriginalLanguage());
		releaseDate.setText(entity.getReleaseDate());
		voteAvg.setText(entity.getVoteAverage());
		Glide.with(getApplicationContext())
				.load(IMAGE_URL + entity.getPoster())
				.into(poster);
		Glide.with(getApplicationContext())
				.load(IMAGE_URL + entity.getPoster())
				.apply(new RequestOptions())
				.into(backdrop);
	}

	@NonNull
	private static DetailViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
	}
}
