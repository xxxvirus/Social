package ua.com.social.service;

import java.util.List;

import ua.com.social.entity.Followers;

public interface FollowersService {

	void save(Followers	followers);
	List<Followers> findAll();
	Followers findOne(int id);
	
	Followers findMemberFollowers(int id);

}
