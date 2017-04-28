package ua.com.social.service;

import ua.com.social.entity.RSAKeysUser;

public interface RSAKeysUserService {

	RSAKeysUser findOne(int id);
	void save(RSAKeysUser key);
}
