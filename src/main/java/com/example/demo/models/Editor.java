package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Editor extends User{
	
	private String editorKey;
	private String salary;
	
	@OneToMany(mappedBy="editor", cascade=CascadeType.ALL)
	private List<News> news;
	
	@ManyToMany
	@JoinTable(name="READER2EDITOR")
	@JsonIgnore
	private List<Reader> readers = new ArrayList<>();

	public Editor() {
	}
	public Editor(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setRole(user.getRole());
		this.setPhone(user.getPhone());
		this.setEmail(user.getEmail());
	}
	
	public String getEditorKey() {
		return editorKey;
	}
	public void setEditorKey(String editorKey) {
		this.editorKey = editorKey;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	public List<Reader> getReaders() {
		return readers;
	}
	public void setReaders(List<Reader> readers) {
		this.readers = readers;
		for(Reader r: readers) {
			r.getEditorsFollowed().add(this);
		}
	}

}

