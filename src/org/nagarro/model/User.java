package org.nagarro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE , mappedBy ="user")
	private List<ImageFile> images = new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ImageFile> getImages() {
		return images;
	}

	public void setImages(List<ImageFile> images) {
		this.images = images;
	}
	
	
	
	

}
