package ua.com.social.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Groups {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nameOfG;
	@Lob
	private String aboutG;
	@ManyToMany(mappedBy = "groups")
	private List<User> users;

	// @OneToMany(mappedBy = "groups")
	// private List<Post> posts = new ArrayList<>();
	public Groups() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameOfG() {
		return nameOfG;
	}

	public void setNameOfG(String nameOfG) {
		this.nameOfG = nameOfG;
	}

	public String getAboutG() {
		return aboutG;
	}

	public void setAboutG(String aboutG) {
		this.aboutG = aboutG;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
		Groups other = (Groups) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
