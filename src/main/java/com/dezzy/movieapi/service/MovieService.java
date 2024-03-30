package com.dezzy.movieapi.service;

import com.dezzy.movieapi.entity.Movie;
import com.dezzy.movieapi.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;
    public List<Movie> getMovies(){
        return repository.findAll();
    }

    public Optional<Movie> getMovieById(ObjectId id){
        return repository.findById(id);
    }
    public Optional<Movie> getMovieByImdbId(String imdbId){
        return repository.findMovieByImdbId(imdbId);
    }
}
