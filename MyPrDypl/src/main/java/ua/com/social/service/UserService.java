package ua.com.social.service;

import ua.com.social.entity.Friends;
import ua.com.social.entity.Groups;
import ua.com.social.entity.User;

public interface UserService {

	void save(User user);
	void update(User user);
	User findByEmail(String email);

	User findOne(int id);
	
	User findMemberFriends(int id);
	void addFriend(User user, Friends friend, int id);
	void removeFriend(User user, Friends friend, int id);
	boolean isAfriend(int id);
	
	User findMemberGroups(int id);
	void followGroup(User user, Groups group, int id);
	void exitFromGroup(User user, Groups group, int id);
}
