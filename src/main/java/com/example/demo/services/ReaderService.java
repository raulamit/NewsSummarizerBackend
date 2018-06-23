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

import com.example.demo.models.Reader;
import com.example.demo.repositories.ReaderRepository;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
public class ReaderService {
	
	@Autowired
	ReaderRepository readRepository;
	
	//CREATE NEW Reader
	@PostMapping("/api/reader")
	public Reader createReader(
			@RequestBody Reader reader) {
		return readRepository.save(reader);
	}
	
	//FIND Reader BY Reader ID
	@GetMapping("/api/reader/{readerId}")
	public Reader findReaderById(
			@PathVariable("readerId") int readerId) {
		
		Optional<Reader> data = readRepository.findById(readerId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Reader
	@PutMapping("/api/reader/{readerId}")
	public Reader updateReader(@PathVariable("readerId") int readerId,
			@RequestBody Reader newReader) {
		
		Optional<Reader> data = readRepository.findById(readerId);
		if(data.isPresent()) {			
			return readRepository.save(newReader);
		}
		return null;
		
	}
		
	//DELETE Reader
	@DeleteMapping("/api/reader/{readerId}")
	public void deleteReader(@PathVariable("readerId") int readerId) {
		readRepository.deleteById(readerId);
		
	}	

}
