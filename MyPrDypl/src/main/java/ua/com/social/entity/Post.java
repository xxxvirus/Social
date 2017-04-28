package ua.com.social.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Lob
	private String text;
	private Date date = new Date();
	// private boolean encrypted = true;
	// private String passPost;
	@ManyToOne
	private User user;
	@ManyToOne
	private Groups groups;

	public Post() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// public boolean getEncrypted() {
	// return encrypted;
	// }
	//
	// public void setEncrypted(boolean encrypted) {
	// this.encrypted = encrypted;
	// }

	// public String getPassPost() {
	// return passPost;
	// }
	//
	// public void setPassPost(String passPost) {
	// this.passPost = passPost;
	// }

}
