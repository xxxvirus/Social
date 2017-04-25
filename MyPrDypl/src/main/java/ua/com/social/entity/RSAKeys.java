package ua.com.social.entity;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class RSAKeys {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Lob
	private PublicKey publickKey;
	@Lob
	private PrivateKey privateKey;
	@OneToOne 
	@JoinColumn(name = "group_id")
	private Groups groups;

	public RSAKeys() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PublicKey getPublickKey() {
		return publickKey;
	}

	public void setPublickKey(PublicKey publickKey) {
		this.publickKey = publickKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

}
