package ua.com.social.service;

import ua.com.social.entity.Followers;
import ua.com.social.entity.Friends;
import ua.com.social.entity.Groups;
import ua.com.social.entity.User;

public interface UserService {

	void save(User user);
	void update(User user);
	User findByEmail(String email);

	User findOne(int id);
	
	User findMemberFriends(int id);
	User findMemberFollowers(int id);
	void follow(User user, Followers follower, int id);
	void deleteRequest(int id);
	void addFriend(User user, Friends friend, int id);
	void removeFriend(User user, Friends friend, int id);
	boolean isAfriend(int id);
	boolean isAfollower(int id);
	boolean isRequested(int id);
	
	User findMemberGroups(int id);
	void followGroup(User user, Groups group, int id);
	void exitFromGroup(User user, Groups group, int id);
}
