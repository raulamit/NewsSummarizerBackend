package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Advertiser extends User{
	
	private String adAgency;
	private String amount;
	
	@OneToMany(mappedBy="advertiser", cascade=CascadeType.ALL)
	private List<Advertisement> advertisements;

	public Advertiser() {
	}
	public Advertiser(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setRole(user.getRole());
		this.setPhone(user.getPhone());
		this.setEmail(user.getEmail());
	}

	public String getAdAgency() {
		return adAgency;
	}

	public void setAdAgency(String adAgency) {
		this.adAgency = adAgency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}
	
	

}

