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
	
	private String category;
    private String views;
    
    @ManyToMany(mappedBy="summaries", cascade=CascadeType.ALL)
	private List<News> news;

    public NewsSummary() {
    	super();
    	this.setStype("news");
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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

