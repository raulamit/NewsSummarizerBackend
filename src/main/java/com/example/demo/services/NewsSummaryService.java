package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Editor;
import com.example.demo.models.News;
import com.example.demo.models.User;
import com.example.demo.repositories.NewsRepository;
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

import javax.servlet.http.HttpSession;

@RestController
//@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@CrossOrigin (origins = "https://newssummarizer-webdev2018-ng.herokuapp.com/", maxAge = 3600, allowCredentials="true")
public class NewsSummaryService {
	
	@Autowired
	NewsSummaryRepository newssummaryRepository;
	@Autowired
	NewsRepository newsRepository;
	
	//CREATE NEW NewsSummary
	@PostMapping("/api/newssummary")
	public NewsSummary createNewsSummary(
            @RequestBody NewsSummary newssummary,
            HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
	    News news = new News();
	    String newsId = newssummary.getSourceId();
	    news.setSourceId(newsId);

	    news.setEditor((new Editor(user)));
        List<News> newsResult = newsRepository.findNewsBySourceId(newsId);
        if(!newsResult.isEmpty()) {
            news = newsResult.get(0);
        } else {
            newsRepository.save(news);
        }
        newssummary.setNews(news);
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
