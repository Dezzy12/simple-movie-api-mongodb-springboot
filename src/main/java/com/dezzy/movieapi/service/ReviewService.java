package com.dezzy.movieapi.service;

import com.dezzy.movieapi.entity.Movie;
import com.dezzy.movieapi.entity.Review;
import com.dezzy.movieapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String body, String imdbId) {
        Review review = repository.insert(new Review(body));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }
}
