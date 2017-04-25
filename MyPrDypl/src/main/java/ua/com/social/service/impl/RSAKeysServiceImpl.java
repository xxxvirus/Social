package ua.com.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.RSAKeysDao;
import ua.com.social.entity.RSAKeys;
import ua.com.social.service.RSAKeysService;

@Service
public class RSAKeysServiceImpl implements RSAKeysService {

	@Autowired
	private RSAKeysDao rsakeysDao;

	@Override
	public RSAKeys findOne(int id) {
		return rsakeysDao.findOne(id);
	}

	@Override
	public void save(RSAKeys key) {
		rsakeysDao.save(key);
	}
	
	
}
