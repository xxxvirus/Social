package ua.com.social.service;

import ua.com.social.entity.User;

public interface UserService {

	void save(User user);

	User findByEmail(String email);

	User findOne(int id);
}
