package id.dikisiswanto.jetpackacademy.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_entities")
public class MovieEntity {
	@PrimaryKey
	@NonNull
	private String id;
	private String title;
	@ColumnInfo(name = "release_date")
	private String releaseDate;
	private String description;
	@ColumnInfo(name = "vote_average")
	private String voteAverage;
	@ColumnInfo(name = "original_language")
	private String originalLanguage;
	private String poster;
	private int type;
	private boolean status = false;

	@Ignore
	public MovieEntity() {
	}

	public MovieEntity(String id, String title, String releaseDate, String description, String voteAverage, String originalLanguage, String poster, int type) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.description = description;
		this.voteAverage = voteAverage;
		this.originalLanguage = originalLanguage;
		this.poster = poster;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
