package com.movierating.dtos;

public class ResponseRatingDto {
	
	private String movieName;
	private float ratingValue;
	//movieId- primary key
	
	public ResponseRatingDto(String movieName, float ratingValue) {
		super();
		this.movieName = movieName;
		this.ratingValue = ratingValue;
	}
	
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public float getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(float ratingValue) {
		this.ratingValue = ratingValue;
	}
	
}
