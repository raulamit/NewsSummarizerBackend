package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NewsSummary extends Summary{
	
	@Enumerated(EnumType.STRING)
	private Category category;
    private String views;
    @Transient
    private String sourceId;
    
    @ManyToOne
	@JsonIgnore
	private News news;

    public NewsSummary() {
    	super();
    	this.setStype("news");
	}

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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
}

