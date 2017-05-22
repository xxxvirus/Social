package ua.com.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.KeyAESDao;
import ua.com.social.entity.KeyAES;
import ua.com.social.service.KeyAESService;

@Service
public class KeyAESServiceImpl implements KeyAESService {

	@Autowired
	private KeyAESDao aesDao;
	
	@Override
	public KeyAES findOne(int id) {
		return aesDao.findOne(id);
	}

	@Override
	public void save(KeyAES keyAes) {
		aesDao.save(keyAes);
	}

}
