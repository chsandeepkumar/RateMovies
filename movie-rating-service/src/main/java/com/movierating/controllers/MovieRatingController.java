package com.movierating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movierating.dtos.RequestRatingDto;
import com.movierating.dtos.ResponseRatingDto;
import com.movierating.services.MovieRatingServiceInterface;

@RestController
@RequestMapping(value="/movie-rating",produces = {"application/json"},consumes = "application/json" )
public class MovieRatingController {
	
	private final MovieRatingServiceInterface movieRatingService;
	
	@Autowired
	public MovieRatingController(MovieRatingServiceInterface movieRatingService) {
		
		this.movieRatingService=movieRatingService;
	}
	
	@RequestMapping(value = "/rating", method = RequestMethod.POST)
	public ResponseEntity<ResponseRatingDto> saveRating(@RequestBody RequestRatingDto ratingDto)
	{
		ResponseRatingDto ratingDtoresponse= this.movieRatingService.saveRating(ratingDto);
		
		return new ResponseEntity<ResponseRatingDto>(ratingDtoresponse,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rating/{movieId}", method = RequestMethod.GET) 
	public ResponseEntity<ResponseRatingDto> getRating(@PathVariable int movieId)
	{
		ResponseRatingDto ratingDtoresponse= this.movieRatingService.getRating(movieId);
		
		return new ResponseEntity<ResponseRatingDto>(ratingDtoresponse,HttpStatus.OK);
	}
	
	

}
