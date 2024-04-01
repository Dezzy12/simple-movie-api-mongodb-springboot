package com.dezzy.movieapi.repository;

import com.dezzy.movieapi.entity.Movie;
import com.dezzy.movieapi.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
