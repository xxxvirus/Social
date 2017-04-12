package ua.com.social.service;

import java.util.List;

import ua.com.social.entity.Friends;

public interface FriendsService {

	void save(Friends friends);
	List<Friends> findAll();
	Friends findOne(int id);
	
	Friends findMemberFriends(int id);
}
