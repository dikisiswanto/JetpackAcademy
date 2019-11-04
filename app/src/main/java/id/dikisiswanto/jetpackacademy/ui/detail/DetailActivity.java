package id.dikisiswanto.jetpackacademy.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import id.dikisiswanto.jetpackacademy.R;
import id.dikisiswanto.jetpackacademy.data.MovieEntity;

public class DetailActivity extends AppCompatActivity {

	public static final String ENTITY_ID = "entity_id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
		Bundle extra = getIntent().getExtras();
		if (extra != null) {
			String entityId = extra.getString(ENTITY_ID);
			if (entityId != null) {
				viewModel.setId(entityId);
				MovieEntity entity = viewModel.getDetails(getApplicationContext());
				populateDetails(entity);
			} else {
				finish();
			}
		}
	}

	private void populateDetails(MovieEntity entity) {
		TextView title = findViewById(R.id.title);
		TextView description = findViewById(R.id.overview);
		TextView originalLanguage = findViewById(R.id.original_language);
		TextView releaseDate = findViewById(R.id.release_date);
		TextView runtime = findViewById(R.id.runtime);
		ImageView poster = findViewById(R.id.poster);
		ImageView backdrop = findViewById(R.id.backdrop);

		title.setText(entity.getTitle());
		description.setText(entity.getDescription());
		originalLanguage.setText(entity.getOriginalLanguage());
		releaseDate.setText(entity.getReleaseDate());
		runtime.setText(entity.getRuntime());
		poster.setImageResource(entity.getPoster());
		backdrop.setImageResource(entity.getPoster());
	}
}
