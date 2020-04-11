package com.movierating.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.movierating.dtos.RequestRatingDto;
import com.movierating.dtos.ResponseRatingDto;
import com.movierating.services.MovieRatingService;
import com.movierating.services.MovieRatingServiceInterface;

@Testable
@RunWith(SpringRunner.class)
public class MovieRatingControllerTests {
	
	private  RequestRatingDto mockRatingDto;
	
	@Mock
	private MovieRatingService mockmovieRatingService;
	
	@InjectMocks
	MovieRatingController movieRatinvControllerInstance;
	@Before(value = "")
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		
	}
	@Test
	public void testMovieratingControlle() throws Exception
	{
		MovieRatingServiceInterface movieRatingService=mock(MovieRatingService.class);
		
		MovieRatingController movieRatinvControllerInstance= new MovieRatingController(movieRatingService);
		assertNotNull(movieRatinvControllerInstance);
	}
	
	@Test
	public void testMovieratingControlle_SaveRating() throws Exception
	{		
				
		when(mockmovieRatingService.saveRating(ArgumentMatchers.any(RequestRatingDto.class))).thenReturn(getMockResponseDto());
		
		ResponseEntity<ResponseRatingDto> ratingDtoResponse=movieRatinvControllerInstance.saveRating(getRequestRatingDto());
		
		assertNotNull(ratingDtoResponse);
		assertEquals(ratingDtoResponse.getBody().getRatingValue(), getRequestRatingDto().getRatingValue());
	}
	
	@Test
	public void testtestMovieratingControlle_GetRating()
	{
		when(mockmovieRatingService.getRating(ArgumentMatchers.anyInt())).thenReturn(getMockResponseDto());
		ResponseEntity<ResponseRatingDto> ratingDtoResponse=movieRatinvControllerInstance.getRating(10);
		assertNotNull(ratingDtoResponse);
		assertEquals(ratingDtoResponse.getBody().getRatingValue(), 4.5f);
		
	}
	private RequestRatingDto getRequestRatingDto() {
		mockRatingDto= new RequestRatingDto();
		mockRatingDto.setRatingValue(4.5f);
		return mockRatingDto;
	}
	private ResponseRatingDto getMockResponseDto() {
		return new ResponseRatingDto("kushi",4.5f);		
	}
	
	

}
