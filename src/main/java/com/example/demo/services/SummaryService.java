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

import com.example.demo.models.Summary;
import com.example.demo.repositories.SummaryRepository;

@RestController
public class SummaryService {
	
	@Autowired
	SummaryRepository summaryRepository;
	
	//CREATE NEW Summary
	@PostMapping("/api/summary")
	public Summary createSummary(
			@RequestBody Summary summary) {
		return summaryRepository.save(summary);
	}
	
	//FIND Summary BY Summary ID
	@GetMapping("/api/summary/{summaryId}")
	public Summary findSummaryById(
			@PathVariable("summaryId") int summaryId) {
		
		Optional<Summary> data = summaryRepository.findById(summaryId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Summary
	@PutMapping("/api/summary/{summaryId}")
	public Summary updateSummary(@PathVariable("summaryId") int summaryId,
			@RequestBody Summary newSummary) {
		
		Optional<Summary> data = summaryRepository.findById(summaryId);
		if(data.isPresent()) {			
			return summaryRepository.save(newSummary);
		}
		return null;
		
	}
		
	//DELETE Summary
	@DeleteMapping("/api/summary/{summaryId}")
	public void deleteSummary(@PathVariable("summaryId") int summaryId) {
		summaryRepository.deleteById(summaryId);
		
	}	


}
