package ua.com.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.RSAKeysUserDao;
import ua.com.social.entity.RSAKeysUser;
import ua.com.social.service.RSAKeysUserService;
@Service
public class RSAKeysUserServiceImpl implements RSAKeysUserService {

	@Autowired
	private RSAKeysUserDao keysDao;
	
	@Override
	public RSAKeysUser findOne(int id) {
		return keysDao.findOne(id);
	}

	@Override
	public void save(RSAKeysUser key) {
		keysDao.save(key);
	}

}
