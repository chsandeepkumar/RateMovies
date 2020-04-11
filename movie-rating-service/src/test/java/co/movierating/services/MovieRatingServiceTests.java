package co.movierating.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.movierating.domain.RatingEntity;
import com.movierating.dtos.RequestRatingDto;
import com.movierating.dtos.ResponseRatingDto;
import com.movierating.repository.MovieRatingRepository;
import com.movierating.services.MovieRatingService;

@Testable
@RunWith(SpringRunner.class)
public class MovieRatingServiceTests {


	@Mock
	private MovieRatingRepository mockMovieRatingRepository; 

	@InjectMocks
	private MovieRatingService mockMovieRatingService;


	@Before(value = "")
	public void inIt()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetRating() {

		when(mockMovieRatingRepository.findById(anyInt())).thenReturn(Optional.of(getMockRatingEntity()));	
		assertTrue(mockMovieRatingService.getRating(100).getMovieName() == "kushi"); 	
	}
	
	@Test
	public void testSaveRating()
	{
		when(mockMovieRatingRepository.findByMovieId(anyInt())).thenReturn(Optional.of(getMockRatingEntity()));
		when(mockMovieRatingRepository.save(ArgumentMatchers.any(RatingEntity.class))).thenReturn(getMockRatingEntity());
		
		ResponseRatingDto responseRatingDto=mockMovieRatingService.saveRating(getMockRequestRatingDto());
		
		assertTrue(responseRatingDto!=null);
		assertTrue(responseRatingDto.getRatingValue()==4.5f);
	}
	
	

	private RatingEntity getMockRatingEntity() {
		return  new RatingEntity(100,"kushi",4.5f);
	}
	private RequestRatingDto getMockRequestRatingDto() {
		RequestRatingDto mockRatingDto= new RequestRatingDto();
		mockRatingDto.setRatingValue(4.5f);
		mockRatingDto.setMovieName("kushi");
		return mockRatingDto;
	}




















}
