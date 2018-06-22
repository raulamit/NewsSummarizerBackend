package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NewsSummary extends Summary{
	
	@Enumerated(EnumType.STRING)
	private Category category;
    private String views;
    
    @ManyToMany(mappedBy="summaries", cascade=CascadeType.ALL)
	private List<News> news;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public List<News> getEditors() {
		return news;
	}

	public void setEditors(List<News> news) {
		this.news = news;
	} 
    
    
    


}

