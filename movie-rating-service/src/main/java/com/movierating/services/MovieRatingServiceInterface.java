package com.movierating.services;


import com.movierating.dtos.RequestRatingDto;
import com.movierating.dtos.ResponseRatingDto;

public interface MovieRatingServiceInterface {
	
	ResponseRatingDto saveRating(RequestRatingDto ratingDto);
	
	ResponseRatingDto getRating(int movieId);

}
