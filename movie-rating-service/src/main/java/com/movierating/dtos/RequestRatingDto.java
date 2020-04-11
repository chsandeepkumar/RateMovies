package com.movierating.dtos;

public class RequestRatingDto {
		
	private String movieName;	
	private float ratingValue;	
	private int movieId;
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
