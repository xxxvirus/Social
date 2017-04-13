package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.social.entity.Friends;

public interface FriendsDao extends JpaRepository<Friends, Integer> {

	@Query("SELECT DISTINCT f FROM Friends f LEFT JOIN FETCH f.users WHERE "
			+ "f.id=:id")
	Friends findMemberFriends(@Param("id") int id);
	
}
