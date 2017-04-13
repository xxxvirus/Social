package ua.com.social.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.social.dao.UserDao;
import ua.com.social.entity.Role;
import ua.com.social.entity.User;
import ua.com.social.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void save(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
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
	
	@PostConstruct
	public void addAdmin(){
		User user = userDao.findByEmail("pavlo94@admin.ua");
		if(user==null){
			user = new User();
			user.setName("Pavlo");
			user.setSurname("Admin");
			user.setCountry("Ukraine");
			user.setCity("Lviv");
			user.setEmail("pavlo94@admin.ua");
			user.setPhoneNumber("0631234567");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userDao.save(user);
		}
	}

	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public User findMemberFriends(int id) {
		return userDao.findMemberFriends(id);
	}

}
