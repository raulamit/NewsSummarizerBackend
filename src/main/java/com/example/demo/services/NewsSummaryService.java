package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.NewsSummary;
import com.example.demo.repositories.NewsSummaryRepository;

@RestController
@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class NewsSummaryService {
	
	@Autowired
	NewsSummaryRepository newssummaryRepository;
	
	//CREATE NEW NewsSummary
	@PostMapping("/api/newssummary")
	public NewsSummary createNewsSummary(
			@RequestBody NewsSummary newssummary) {
		return newssummaryRepository.save(newssummary);
	}
	
	//FIND NewsSummary BY NewsSummary ID
	@GetMapping("/api/newssummary/{newssummaryId}")
	public NewsSummary findNewsSummaryById(
			@PathVariable("newssummaryId") int newssummaryId) {
		
		Optional<NewsSummary> data = newssummaryRepository.findById(newssummaryId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE NewsSummary
	@PutMapping("/api/newssummary/{newssummaryId}")
	public NewsSummary updateNewsSummary(@PathVariable("newssummaryId") int newssummaryId,
			@RequestBody NewsSummary newNewsSummary) {
		
		Optional<NewsSummary> data = newssummaryRepository.findById(newssummaryId);
		if(data.isPresent()) {			
			return newssummaryRepository.save(newNewsSummary);
		}
		return null;
		
	}
		
	//DELETE NewsSummary
	@DeleteMapping("/api/newssummary/{newssummaryId}")
	public void deleteNewsSummary(@PathVariable("newssummaryId") int newssummaryId) {
		newssummaryRepository.deleteById(newssummaryId);
		
	}	


}
