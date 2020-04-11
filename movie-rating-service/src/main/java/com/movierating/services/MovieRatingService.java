package com.movierating.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierating.domain.RatingEntity;
import com.movierating.dtos.RequestRatingDto;
import com.movierating.dtos.ResponseRatingDto;
import com.movierating.repository.MovieRatingRepository;

@Service
public class MovieRatingService implements MovieRatingServiceInterface {

	private final MovieRatingRepository movieRatingRepository;	

	@Autowired
	public MovieRatingService(MovieRatingRepository movieRatingRepository) {
		this.movieRatingRepository=movieRatingRepository;		
	}

	public ResponseRatingDto saveRating(RequestRatingDto requestRatingDto) {

		RatingEntity newRatingEntity=mapToRatingEntity(requestRatingDto);

		RatingEntity  currentRatingEntity=getCurrentRating(newRatingEntity.getMovieId());

		setRatingEntityWithUpDatedRatingValues(newRatingEntity, currentRatingEntity);


		RatingEntity savedEntity=this.movieRatingRepository.save(currentRatingEntity);

		return mapToResponseDto(savedEntity);
	}

	public ResponseRatingDto getRating(int movieId) {

		Optional<RatingEntity> availableRatingEntity=this.movieRatingRepository.findById(movieId);

		return mapToResponseDto(availableRatingEntity.get());//TODO Need to convert optional mapper
	}

	private void setRatingEntityWithUpDatedRatingValues(RatingEntity newRatingEntity,
			RatingEntity currentRatingEntity) 
	{
		setRatingMovieName(newRatingEntity,currentRatingEntity);

		setRatingValue(newRatingEntity, currentRatingEntity);

		setRatingCounter(currentRatingEntity);
	}	

	private void setRatingMovieName(RatingEntity newRatingEntity, RatingEntity currentRatingEntity) {

		if(newRatingEntity.getMovieId()!=currentRatingEntity.getMovieId()) {

			currentRatingEntity.setMovieName(newRatingEntity.getMovieName());
			currentRatingEntity.setMovieId(newRatingEntity.getMovieId());
		}
	}

	private void setRatingCounter(RatingEntity currentRatingEntity) {

		currentRatingEntity.setRatingCounter(calculateTotalMembersContributedeForRating(currentRatingEntity));
	}

	private void setRatingValue(RatingEntity newRatingEntity, RatingEntity currentRatingEntity) {

		float calcuatedRatingValue=calculateRating(newRatingEntity,currentRatingEntity);	
		currentRatingEntity.setRatingValue(calcuatedRatingValue);
	}

	private RatingEntity getCurrentRating(int movieId)
	{
		Optional<RatingEntity> currentRatingEntity= this.movieRatingRepository.findByMovieId(movieId);	
		if(currentRatingEntity.isPresent())
			return currentRatingEntity.get();

		return new RatingEntity();
	}

	private float calculateRating(RatingEntity newRating, RatingEntity currentRating) {


		return calculateTotalRatings(currentRating.getRatingValue(), newRating.getRatingValue())/
				calculateTotalMembersContributedeForRating(currentRating);

	}

	private float calculateTotalRatings(float currentrating,float newrating)
	{
		return currentrating+newrating;
	}

	private int calculateTotalMembersContributedeForRating(RatingEntity currentRating) {

		return currentRating.getRatingCounter()+1;

	}

	private RatingEntity mapToRatingEntity(RequestRatingDto requestRatingDto)
	{
		return  new RatingEntity(requestRatingDto.getMovieId(),
				requestRatingDto.getMovieName(),
				requestRatingDto.getRatingValue());		

	}

	private ResponseRatingDto mapToResponseDto(RatingEntity ratingEntity)
	{

		return new ResponseRatingDto(ratingEntity.getMovieName(),ratingEntity.getRatingValue());
	}
}
