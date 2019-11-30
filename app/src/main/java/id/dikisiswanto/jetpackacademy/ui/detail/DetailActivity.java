package id.dikisiswanto.jetpackacademy.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.source.local.entity.MovieEntity;
import id.dikisiswanto.jetpackacademy.viewmodel.ViewModelFactory;

import static id.dikisiswanto.jetpackacademy.utils.Constant.IMAGE_URL;

public class DetailActivity extends AppCompatActivity {

	public static final String ENTITY_ID = "entity_id";
	public static final String ENTITY_TYPE = "entity_type";
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.progress)
	ProgressBar progressBar;
	@BindView(R.id.content)
	LinearLayout content;
	@BindView(R.id.title)
	TextView title;
	@BindView(R.id.overview)
	TextView description;
	@BindView(R.id.original_language)
	TextView originalLanguage;
	@BindView(R.id.release_date)
	TextView releaseDate;
	@BindView(R.id.vote_average)
	TextView voteAvg;
	@BindView(R.id.poster)
	ImageView poster;
	@BindView(R.id.backdrop)
	ImageView backdrop;
	private DetailViewModel viewModel;
	private Menu menu;

	@NonNull
	private static DetailViewModel obtainViewModel(FragmentActivity activity) {
		// Use a Factory to inject dependencies into the ViewModel
		ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
		return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		ButterKnife.bind(this);

		initToolbar();
		showProgress();

		viewModel = obtainViewModel(this);
		extractBundle();
		observeData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.favorite_button_menu, menu);
		this.menu = menu;
		viewModel.detail.observe(this, detail -> {
			if (detail != null) {
				switch (detail.status) {
					case LOADING:
						showProgress();
						break;
					case SUCCESS:
						if (detail.data != null) {
							hideProgress();
							boolean status = detail.data.getStatus();
							setFavoriteStatus(status);
						}
						break;
					case ERROR:
						hideProgress();
						Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
				}
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private void setFavoriteStatus(boolean status) {
		if (menu == null) return;
		MenuItem menuItem = menu.findItem(R.id.favorite_button);
		if (status) {
			menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white));
			menuItem.setTitle(R.string.remove_from_favorite);
		} else {
			menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white));
			menuItem.setTitle(R.string.add_to_favorite);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.favorite_button) {
			viewModel.setFavorite();
		} else {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void extractBundle() {
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
	}

	private void observeData() {
		viewModel.detail.observe(this, entity -> {
			if (entity.data != null) {
				switch (entity.status) {
					case LOADING:
						showProgress();
						break;
					case SUCCESS:
						hideProgress();
						populateDetails(entity.data);
						break;
					case ERROR:
						hideProgress();
						Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void initToolbar() {
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
	}

	private void hideProgress() {
		progressBar.setVisibility(View.GONE);
		content.setVisibility(View.VISIBLE);
	}

	private void showProgress() {
		progressBar.setVisibility(View.VISIBLE);
		content.setVisibility(View.INVISIBLE);
	}


	private void populateDetails(MovieEntity entity) {
		title.setText(entity.getTitle());
		description.setText(entity.getDescription());
		originalLanguage.setText(entity.getOriginalLanguage());
		releaseDate.setText(entity.getReleaseDate());
		voteAvg.setText(entity.getVoteAverage());
		Glide.with(getApplicationContext())
				.load(IMAGE_URL + entity.getPoster())
				.placeholder(R.drawable.ic_loading)
				.error(R.drawable.ic_error)
				.into(poster);
		Glide.with(getApplicationContext())
				.load(IMAGE_URL + entity.getPoster())
				.apply(new RequestOptions())
				.placeholder(R.drawable.ic_loading)
				.error(R.drawable.ic_error)
				.into(backdrop);
	}
}
