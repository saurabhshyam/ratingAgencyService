package model;

public class MovieList {

	private Long assetId;
	private String title;
	private Long year;

	public MovieList() {
		super();

	}

	public MovieList(Long assetId, String title, Long year) {
		super();
		this.assetId = assetId;
		this.title = title;
		this.year = year;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

}
