package com.Foodies.service;

import java.util.List;

import com.Foodies.Exception.ReviewException;
import com.Foodies.model.Review;
import com.Foodies.model.User;
import com.Foodies.request.ReviewRequest;

public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
}
