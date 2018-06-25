package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.NewsSummary;
import com.example.demo.models.Reader;
import com.example.demo.models.User;
import com.example.demo.repositories.NewsSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600, allowCredentials= "true")
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	NewsSummaryRepository newsSummaryRepository;
	
	//CREATE NEW Review
	@PostMapping("/api/review")
	public Review createReview(
			@RequestBody Review review) {
		return reviewRepository.save(review);
	}

	@PostMapping("/api/newssummary/{summaryId}/review")
	public Review createReview(
			HttpSession session,
			@PathVariable("summaryId") int summaryId,
			@RequestBody Review newReview) {
		Optional<NewsSummary> data = newsSummaryRepository.findById(summaryId);
		User user = (User) session.getAttribute("currentUser");
		if(data.isPresent()) {
			NewsSummary newsSummary = data.get();
			newReview.setSummary(newsSummary);
			newReview.setReader(new Reader(user));
			return reviewRepository.save(newReview);
		}
		return null;
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
