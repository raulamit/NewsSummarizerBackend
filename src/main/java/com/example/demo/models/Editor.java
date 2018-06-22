package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Editor extends User{
	
	private String editorKey;
	private String salary;
	
	@OneToMany(mappedBy="editor", cascade=CascadeType.ALL)
	private List<News> news;

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
		

}

