package com.movierating.movieloadservice.services;

import com.movierating.movieloadservice.Constants;
import com.movierating.movieloadservice.models.Movie;
import com.movierating.movieloadservice.models.MovieDBMovie;
import com.movierating.movieloadservice.movieapi.MovieService;
import com.movierating.movieloadservice.repository.MovieLoadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieLoadService {

    @Autowired
    private MovieLoadRepository movieLoadRepository;

    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void loadCurrentlyPlayingMovies() {
        List<MovieDBMovie> movieList = movieService.getCurrentlyPlayingMovies();
        List<Movie> movies = getAllMovies(movieList);
        movieLoadRepository.saveAll(movies);
        savePosterForTheMovies(movies);
        log.info("{0} movies loaded to database", movies.size());
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movieLoadRepository.findAll().forEach(m -> movies.add(m));
        return movies;
    }

    private List<Movie> getAllMovies(List<MovieDBMovie> moviesFromService) {
        List<Movie> movies = new ArrayList<>();

        //get all Movie ID's
        Set<String> movieId = getMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toSet());

        // Just not to overwrite the existing data every day as per scheduled job
        moviesFromService.stream()
                .filter(m -> !movieId.contains(m.getId()))
                .forEach( m -> {
                    Movie movie = new Movie();
                    BeanUtils.copyProperties(m, movie);
                    movies.add(movie);
                });

        return movies;
    }

    private void savePosterForTheMovies(List<Movie> movies) {
        movies.stream()
                .forEach(m -> {
                    try {
                        ResponseEntity<byte[]> responseImage = movieService.getImageByPosterPath(m.getPosterPath());
                        File outputFile = new File(Constants.imagesSavedLocation + m.getTitle() + ".jpg");
                        OutputStream outputStream = new FileOutputStream(outputFile);
                        outputStream.write(responseImage.getBody());
                    }catch (Exception e){
                        log.error("problem saving images", e);
                    }
                });
    }
}
