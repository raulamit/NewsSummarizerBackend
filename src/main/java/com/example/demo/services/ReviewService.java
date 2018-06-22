package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;

@RestController
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	//CREATE NEW Review
	@PostMapping("/api/review")
	public Review createReview(
			@RequestBody Review review) {
		return reviewRepository.save(review);
	}
	
	//FIND Review BY Review ID
	@GetMapping("/api/review/{reviewId}")
	public Review findReviewById(
			@PathVariable("reviewId") int reviewId) {
		
		Optional<Review> data = reviewRepository.findById(reviewId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Review
	@PutMapping("/api/review/{reviewId}")
	public Review updateReview(@PathVariable("reviewId") int reviewId,
			@RequestBody Review newReview) {
		
		Optional<Review> data = reviewRepository.findById(reviewId);
		if(data.isPresent()) {			
			return reviewRepository.save(newReview);
		}
		return null;
		
	}
		
	//DELETE Review
	@DeleteMapping("/api/review/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") int reviewId) {
		reviewRepository.deleteById(reviewId);
		
	}	


}
