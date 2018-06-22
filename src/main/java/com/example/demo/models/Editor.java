package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Editor extends User{
	
	private String editorKey;
	private String salary;
	
	@ManyToMany
	@JoinTable(name="EDITOR2SUMMARIES")
	private List<NewsSummary> summaries;

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

	public List<NewsSummary> getSummaries() {
		return summaries;
	}

	public void setSummaries(List<NewsSummary> summaries) {
		this.summaries = summaries;
	}
	
	

}

