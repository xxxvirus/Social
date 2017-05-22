package ua.com.social.service;

import ua.com.social.entity.KeyAES;

public interface KeyAESService {

	KeyAES findOne(int id);
	void save(KeyAES keyAes);
}
