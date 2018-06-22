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

import com.example.demo.models.Editor;
import com.example.demo.repositories.EditorRepository;

@RestController
public class EditorService {
		
	@Autowired
	EditorRepository editorRepository;
	
	//CREATE NEW Editor
	@PostMapping("/api/editor")
	public Editor createEditor(
			@RequestBody Editor editor) {
		return editorRepository.save(editor);
	}
	
	//FIND Editor BY Editor ID
	@GetMapping("/api/editor/{editorId}")
	public Editor findEditorById(
			@PathVariable("editorId") int eid) {
		
		Optional<Editor> data = editorRepository.findById(eid);
		if(data.isPresent())
			return data.get();
			
		return null;
	}
	
	//UPDATE Editor
	@PutMapping("/api/editor/{editorId}")
	public Editor updateEditor(@PathVariable("editorId") int editorId,
			@RequestBody Editor newEditor) {
		
		Optional<Editor> data = editorRepository.findById(editorId);
		if(data.isPresent()) {			
			return editorRepository.save(newEditor);
		}
		return null;
		
	}
		
	//DELETE Editor
	@DeleteMapping("/api/editor/{editorId}")
	public void deleteEditor(@PathVariable("editorId") int editorId) {
		editorRepository.deleteById(editorId);
		
	}	


}
