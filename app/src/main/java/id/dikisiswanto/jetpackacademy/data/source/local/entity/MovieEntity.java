package id.dikisiswanto.jetpackacademy.data.source.local.entity;

public class MovieEntity {
	private String id;
	private String title;
	private String releaseDate;
	private String description;
	private String voteAverage;
	private String originalLanguage;
	private String poster;

	public MovieEntity() {
	}

	public MovieEntity(String id, String title, String releaseDate, String description, String voteAverage, String originalLanguage, String poster) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.description = description;
		this.voteAverage = voteAverage;
		this.originalLanguage = originalLanguage;
		this.poster = poster;
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
}
