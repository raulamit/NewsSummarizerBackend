package com.example.demo.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Advertisement;
import com.example.demo.models.Advertiser;
import com.example.demo.models.User;
import com.example.demo.repositories.AdvertisementRepository;
import com.example.demo.repositories.AdvertiserRepository;

@RestController
@CrossOrigin (origins = "https://newssummarizer-webdev2018-ng.herokuapp.com", maxAge = 3600, allowCredentials="true")
//@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AdvertisementService {
	
	@Autowired
	AdvertisementRepository advertisementRepository;
	
	@Autowired
	AdvertiserRepository advertiserRepository;
	
//	//CREATE NEW Advertisement
//	@PostMapping("/api/advertisement")
//	public Advertisement createAdvertisement(
//			@RequestBody Advertisement advertisement) {
//		return advertisementRepository.save(advertisement);
//	}
	
	//CREATE NEW Advertisement For Advertiser
		@PostMapping("/api/advertisement")
		public Advertisement createAdvertisementForAdvertiser(
				@RequestBody Advertisement advertisement,
				HttpSession session) {
			User advertiser =  (User) session.getAttribute("currentUser");
			advertisement.setAdvertiser(new Advertiser(advertiser));
			return advertisementRepository.save(advertisement);
		}
	
	//FIND Advertisement BY Advertisement ID
	@GetMapping("/api/advertisement/{advertisementId}")
	public Advertisement findAdvertisementById(
			@PathVariable("advertisementId") int advertisementId) {
		
		Optional<Advertisement> data = advertisementRepository.findById(advertisementId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Advertisement
	@PutMapping("/api/advertisement/{advertisementId}")
	public Advertisement updateAdvertisement(@PathVariable("advertisementId") int advertisementId,
			@RequestBody Advertisement newAdvertisement) {
		
		Optional<Advertisement> data = advertisementRepository.findById(advertisementId);
		if(data.isPresent()) {			
			return advertisementRepository.save(newAdvertisement);
		}
		return null;
		
	}
		
	//DELETE Advertisement
	@DeleteMapping("/api/advertisement/{advertisementId}")
	public void deleteAdvertisement(@PathVariable("advertisementId") int advertisementId) {
		advertisementRepository.deleteById(advertisementId);
		
	}	


}
