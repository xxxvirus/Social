package ua.com.social.service.impl;

import java.util.List;
import java.util.stream.Stream;

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

	@Override
	public boolean isMyFriend(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		User users = userDao.findMemberFriends(id);
		List<Friends> list = users.getFriends();
		for (Friends friends : list) {
			Stream<User> friend = friends.getUsers().stream()
					.filter(s -> s.getId() == userId);
			if (friend != null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isMyFriendConf(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		Friends friend = friendsDao.findMemberFriends(id);
		List<User> list = friend.getUsers();
		for (User users : list) {
			Stream<Friends> friends = users.getFriends().stream()
					.filter(s -> s.getId() == userId);
			if (friends != null) {
				return true;
			}
		}
		return false;
	}
}
