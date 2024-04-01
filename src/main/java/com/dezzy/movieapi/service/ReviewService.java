package com.dezzy.movieapi.service;

import com.dezzy.movieapi.entity.Movie;
import com.dezzy.movieapi.entity.Review;
import com.dezzy.movieapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId) {
        Review review = repository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();;
        return review;
    }

    public List<Review> getReviewsByImdbId(String imdbId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));
        Movie movie = mongoTemplate.findOne(query, Movie.class);
        if (movie != null) {
            return movie.getReviewIds();
        }
        return Collections.emptyList();
    }
}
