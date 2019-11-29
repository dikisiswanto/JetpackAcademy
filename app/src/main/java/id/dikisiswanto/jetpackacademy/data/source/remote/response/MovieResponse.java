package id.dikisiswanto.jetpackacademy.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponse implements Parcelable {
	private String id;
	private String title;
	private String releaseDate;
	private String description;
	private String voteAverage;
	private String originalLanguage;
	private String poster;
	public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
		@Override
		public MovieResponse createFromParcel(Parcel source) {
			return new MovieResponse(source);
		}

		@Override
		public MovieResponse[] newArray(int size) {
			return new MovieResponse[size];
		}
	};

	public MovieResponse() {
	}

	private int type;

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

	public MovieResponse(String id, String title, String releaseDate, String description, String voteAverage, String originalLanguage, String poster, int type) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.description = description;
		this.voteAverage = voteAverage;
		this.originalLanguage = originalLanguage;
		this.poster = poster;
		this.type = type;
	}

	protected MovieResponse(Parcel in) {
		this.id = in.readString();
		this.title = in.readString();
		this.releaseDate = in.readString();
		this.description = in.readString();
		this.voteAverage = in.readString();
		this.originalLanguage = in.readString();
		this.poster = in.readString();
		this.type = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.title);
		dest.writeString(this.releaseDate);
		dest.writeString(this.description);
		dest.writeString(this.voteAverage);
		dest.writeString(this.originalLanguage);
		dest.writeString(this.poster);
		dest.writeInt(this.type);
	}
}
