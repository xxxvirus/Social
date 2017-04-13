package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.social.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.email=:param")
	User findByEmail(@Param("param") String email);
	
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.friends WHERE "
			+ "u.id=:id")
	User findMemberFriends(@Param("id") int id);
}
