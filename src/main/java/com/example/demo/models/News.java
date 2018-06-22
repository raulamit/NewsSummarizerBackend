package com.example.demo.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String apiUrl;
    private String type;
    private String category;
    private String sectionName;
    private String webTitle;
    private String webUrl;
    private String pillarName;
    private Date webPublicationDate;
    
    @OneToMany(mappedBy="news",  cascade=CascadeType.ALL)
    private List<NewsSummary> summaries;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getWebTitle() {
		return webTitle;
	}
	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getPillarName() {
		return pillarName;
	}
	public void setPillarName(String pillarName) {
		this.pillarName = pillarName;
	}
	public Date getWebPublicationDate() {
		return webPublicationDate;
	}
	public void setWebPublicationDate(Date webPublicationDate) {
		this.webPublicationDate = webPublicationDate;
	}
	public List<NewsSummary> getSummaries() {
		return summaries;
	}
	public void setSummaries(List<NewsSummary> summaries) {
		this.summaries = summaries;
	}
    
    
   
}

