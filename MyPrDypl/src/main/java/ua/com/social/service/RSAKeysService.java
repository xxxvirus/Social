package ua.com.social.service;

import ua.com.social.entity.RSAKeys;

public interface RSAKeysService {

	RSAKeys findOne(int id);
	void save(RSAKeys key);
}
