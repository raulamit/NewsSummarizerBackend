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

import com.example.demo.models.Advertiser;
import com.example.demo.repositories.AdvertiserRepository;

@RestController
public class AdvertiserService {
	
	@Autowired
	AdvertiserRepository advertiserRepository;
	
	//CREATE NEW Advertiser
	@PostMapping("/api/advertiser")
	public Advertiser createAdvertiser(
			@RequestBody Advertiser advertiser) {
		return advertiserRepository.save(advertiser);
	}
	
	//FIND Advertiser BY Advertiser ID
	@GetMapping("/api/advertiser/{advertiserId}")
	public Advertiser findAdvertiserById(
			@PathVariable("advertiserId") int advertiserId) {
		
		Optional<Advertiser> data = advertiserRepository.findById(advertiserId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Advertiser
	@PutMapping("/api/advertiser/{advertiserId}")
	public Advertiser updateAdvertiser(@PathVariable("advertiserId") int advertiserId,
			@RequestBody Advertiser newAdvertiser) {
		
		Optional<Advertiser> data = advertiserRepository.findById(advertiserId);
		if(data.isPresent()) {			
			return advertiserRepository.save(newAdvertiser);
		}
		return null;
		
	}
		
	//DELETE Advertiser
	@DeleteMapping("/api/advertiser/{advertiserId}")
	public void deleteAdvertiser(@PathVariable("advertiserId") int advertiserId) {
		advertiserRepository.deleteById(advertiserId);
		
	}	

}
