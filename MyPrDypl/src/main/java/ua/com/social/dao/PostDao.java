package ua.com.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.social.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

	@Query("select p from Post p where p.user.id=?1 ORDER BY p.id DESC")
	List<Post> findByUserId(int id);
	@Query("select p from Post p where p.groups.id=?1 ORDER BY p.id DESC")
	List<Post> findByGroupId(int id);
}
