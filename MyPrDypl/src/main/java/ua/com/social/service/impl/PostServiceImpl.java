package ua.com.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.PostDao;
import ua.com.social.entity.Post;
import ua.com.social.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostDao postDao;
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	@Override
	public void save(Post post) {
//		post.setPassPost(encoder.encode(post.getPassPost()));
		postDao.save(post);
	}

	@Override
	public Post findOne(int id) {
		return postDao.findOne(id);
	}

	@Override
	public List<Post> findAll() {
		return postDao.findAll();
	}

	@Override
	public void delete(int id) {
		postDao.delete(id);
	}

	@Override
	public List<Post> findByUserId(int id) {
		return postDao.findByUserId(id);
	}

}
