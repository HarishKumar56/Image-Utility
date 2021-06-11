package org.nagarro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="image_file")
public class ImageFile {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="image_id")
	private int imageId;
	
	@Column(name ="file_name")
	private String fileName;
	
	@Column(name="size")
	private long size;
	
	@Lob
	@Column(name ="image")
	private byte[] image;

	@ManyToOne()
	@JoinColumn(name ="user_id")
	private User user;


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ImageFile [fileName=" + fileName + ", size=" + size + "]";
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	


	


	
	
	
	
	

}
