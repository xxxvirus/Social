package ua.com.social.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class KeyAES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Lob
	private String genKey;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public KeyAES() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenKey() {
		return genKey;
	}

	public void setGenKey(String genKey) {
		this.genKey = genKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyAES other = (KeyAES) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
