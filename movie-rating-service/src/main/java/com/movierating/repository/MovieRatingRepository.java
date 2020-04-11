package com.movierating.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movierating.domain.RatingEntity;

@Repository
public interface MovieRatingRepository extends  CrudRepository<RatingEntity,Integer> {
	
	 	  Optional<RatingEntity> findByMovieId(int movieId);

}
