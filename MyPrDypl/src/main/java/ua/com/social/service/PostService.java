package ua.com.social.service;

import java.util.List;

import ua.com.social.entity.Post;

public interface PostService {

	void save(Post post);
	Post findOne(int id);
	List<Post> findAll();
	void delete(int id);
	
	List<Post> findByUserId(int id);
}
