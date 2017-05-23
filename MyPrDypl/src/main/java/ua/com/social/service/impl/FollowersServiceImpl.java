package ua.com.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.FollowersDao;
import ua.com.social.dao.UserDao;
import ua.com.social.entity.Followers;
import ua.com.social.service.FollowersService;

@Service
public class FollowersServiceImpl implements FollowersService {

	@Autowired
	private FollowersDao followersDao;
	@Autowired
	private UserDao userDao;

	@Override
	public void save(Followers followers) {
		followersDao.save(followers);
	}

	@Override
	public List<Followers> findAll() {
		return followersDao.findAll();
	}

	@Override
	public Followers findOne(int id) {
		return followersDao.findOne(id);
	}

	@Override
	public Followers findMemberFollowers(int id) {
		return followersDao.findMemberFollowers(id);
	}

}
