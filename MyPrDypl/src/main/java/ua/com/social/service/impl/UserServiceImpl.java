package ua.com.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.social.dao.FollowersDao;
import ua.com.social.dao.FriendsDao;
import ua.com.social.dao.GroupsDao;
import ua.com.social.dao.UserDao;
import ua.com.social.entity.Followers;
import ua.com.social.entity.Friends;
import ua.com.social.entity.Groups;
import ua.com.social.entity.Role;
import ua.com.social.entity.User;
import ua.com.social.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendsDao friendsDao;
	@Autowired
	private FollowersDao followersDao;
	@Autowired
	private GroupsDao groupsDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void save(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
		Friends friend = new Friends();
		friend.setId(user.getId());
		friendsDao.save(friend);
		Followers follower = new Followers();
		follower.setId(user.getId());
		followersDao.save(follower);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.findByEmail(username);
	}

	// @PostConstruct
	// public void addAdmin(){
	// User user = userDao.findByEmail("pavlo94@admin.ua");
	// if(user==null){
	// user = new User();
	// user.setName("Pavlo");
	// user.setSurname("Admin");
	// user.setCountry("Ukraine");
	// user.setCity("Lviv");
	// user.setEmail("pavlo94@admin.ua");
	// user.setPhoneNumber("0631234567");
	// user.setPassword(encoder.encode("admin"));
	// user.setRole(Role.ROLE_ADMIN);
	// userDao.save(user);
	// }
	// }

	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public User findMemberFriends(int id) {
		return userDao.findMemberFriends(id);
	}

	@Override
	public void addFriend(User user, Friends friend, int id) {
		int myId = user.getId();
		user = userDao.findMemberFriends(myId);
		friend = friendsDao.findOne(id);
		user.getFriends().add(friend);
		userDao.save(user);
		User user2 = userDao.findOne(id);
		user2 = userDao.findMemberFriends(id);
		friend = friendsDao.findOne(myId);
		user2.getFriends().add(friend);
		userDao.save(user2);
		user = userDao.findMemberFollowers(id);
		user.getFollowers().removeIf(f -> f.getId() == myId);
		userDao.save(user);
	}

	@Override
	public void removeFriend(User user, Friends friend, int id) {
		int myId = user.getId();
		user = userDao.findMemberFriends(myId);
		user.getFriends().removeIf(s -> s.getId() == id);
		userDao.save(user);
		User user2 = userDao.findMemberFriends(id);
		user2.getFriends().removeIf(s -> s.getId() == myId);
		userDao.save(user2);
	}

	@Override
	public User findMemberGroups(int id) {
		return userDao.findMemberGroups(id);
	}

	@Override
	public void exitFromGroup(User user, Groups group, int id) {
		int myId = user.getId();
		user = userDao.findMemberGroups(myId);
		user.getGroups().removeIf(s -> s.getId() == id);
		userDao.save(user);
		group = groupsDao.findMemberInGroup(id);
		group.getUsers().removeIf(s -> s.getId() == myId);
		groupsDao.save(group);

	}

	@Override
	public void followGroup(User user, Groups group, int id) {
		int myId = user.getId();
		user = userDao.findMemberGroups(myId);
		group = groupsDao.findOne(id);
		user.getGroups().add(group);
		userDao.save(user);
		group = groupsDao.findMemberInGroup(id);
		group.getUsers().add(user);
		groupsDao.save(group);
	}

	@Override
	public boolean isAfriend(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		User friend = userDao.findMemberFriends(id);
		List<Friends> list = friend.getFriends();
		for (Friends friends : list) {
			if (friends.getId() == userId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(User user) {
		userDao.saveAndFlush(user);
	}

	@Override
	public void follow(User user, Followers follower, int id) {
		int myId = user.getId();
		user = userDao.findMemberFollowers(myId);
		follower = followersDao.findOne(id);
		user.getFollowers().add(follower);
		userDao.save(user);
	}

	@Override
	public User findMemberFollowers(int id) {
		return userDao.findMemberFollowers(id);
	}

	@Override
	public boolean isAfollower(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		User follower = userDao.findMemberFollowers(id);
		List<Followers> list = follower.getFollowers();
		for (Followers followers : list) {
			if (followers.getId() == userId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isRequested(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		Followers follower = followersDao.findMemberFollowers(id);
		List<User> list = follower.getUsers();
		for (User users : list) {
			if (users.getId() == userId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteRequest(int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int myId = user.getId();
		user = userDao.findMemberFollowers(myId);
		user.getFollowers().removeIf(s -> s.getId() == id);
		userDao.save(user);
	}

}
