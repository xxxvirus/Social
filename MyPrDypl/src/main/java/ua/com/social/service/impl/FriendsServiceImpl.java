package ua.com.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.com.social.dao.FriendsDao;
import ua.com.social.dao.UserDao;
import ua.com.social.entity.Friends;
import ua.com.social.entity.User;
import ua.com.social.service.FriendsService;
@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	private FriendsDao friendsDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public void save(Friends friends) {
		friendsDao.save(friends);
	}

	@Override
	public List<Friends> findAll() {
		return friendsDao.findAll();
	}

	@Override
	public Friends findMemberFriends(int id) {
		return friendsDao.findMemberFriends(id);
	}

	@Override
	public Friends findOne(int id) {
		return friendsDao.findOne(id);
	}

}
