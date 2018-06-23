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

import com.example.demo.models.News;
import com.example.demo.repositories.NewsRepository;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
public class NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	//CREATE NEW News
	@PostMapping("/api/news")
	public News createNews(
			@RequestBody News news) {
		return newsRepository.save(news);
	}
	
	//FIND News BY News ID
	@GetMapping("/api/news/{newsId}")
	public News findNewsById(
			@PathVariable("newsId") int newsId) {
		
		Optional<News> data = newsRepository.findById(newsId);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE News
	@PutMapping("/api/news/{newsId}")
	public News updateNews(@PathVariable("newsId") int newsId,
			@RequestBody News newNews) {
		
		Optional<News> data = newsRepository.findById(newsId);
		if(data.isPresent()) {			
			return newsRepository.save(newNews);
		}
		return null;
		
	}
		
	//DELETE News
	@DeleteMapping("/api/news/{newsId}")
	public void deleteNews(@PathVariable("newsId") int newsId) {
		newsRepository.deleteById(newsId);
		
	}	


}
