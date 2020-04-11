package com.movierating.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "movie_rating")
public class RatingEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "rating_id")
	private int id;

	@Column(name = "movie_id")
	private int movieId;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "rating_value")
	private float ratingValue;

	@Column(name = "rating_counter")
	private int ratingCounter;

	public RatingEntity() {}
	public RatingEntity(int movieId, String movieName, float ratingValue) {
		super();		
		this.movieId = movieId;
		this.movieName = movieName;
		this.ratingValue = ratingValue;		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getRatingCounter() {
		return ratingCounter;
	}
	public void setRatingCounter(int ratingCounter) {
		this.ratingCounter = ratingCounter;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
