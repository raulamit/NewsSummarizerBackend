package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Reader extends User{
	
	private Boolean premium;
	@OneToMany(mappedBy="reader", cascade=CascadeType.ALL)
	private List<Review> reviews;

	public Reader() {
	}

	public Reader(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setRole(user.getRole());
		this.setPhone(user.getPhone());
		this.setEmail(user.getEmail());
    }

    public Boolean getPremium() {
		return premium;
	}
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	} 

}
