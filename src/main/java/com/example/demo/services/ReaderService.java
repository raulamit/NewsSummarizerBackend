package com.example.demo.services;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.models.Editor;
import com.example.demo.models.Reader;
import com.example.demo.repositories.EditorRepository;
import com.example.demo.repositories.ReaderRepository;

@RestController
@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600, allowCredentials= "true")
//@CrossOrigin (origins = "https://newssummarizer-webdev2018-ng.herokuapp.com", maxAge = 3600, allowCredentials="true")
public class ReaderService {
	
	@Autowired
	ReaderRepository readRepository;
	
	@Autowired
	EditorRepository editorRepository;
	
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
	
	//Follow EDITOR
	@PutMapping("/api/followeditor")
	public Reader followEditor(@RequestBody Editor editor,
			HttpSession session) {
		
		Reader reader = (Reader) session.getAttribute("currentUser");
		List<Editor> followedEditors = reader.getEditorsFollowed();
		followedEditors.add(editor);
		reader.setEditorsFollowed(followedEditors);
		readRepository.save(reader);
		return readRepository.findById(reader.getId()).get();
	}
	
	//unFollow EDITOR
	@PutMapping("/api/unfolloweditor")
	public Reader unfollowEditor(@RequestBody Editor editor,
			HttpSession session) {
		
		Reader reader = (Reader) session.getAttribute("currentUser");
		List<Editor> followedEditors = reader.getEditorsFollowed();
		followedEditors.removeIf(e -> e.getId()==editor.getId());
		reader.setEditorsFollowed(followedEditors);
		readRepository.save(reader);
		return readRepository.findById(reader.getId()).get();
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
