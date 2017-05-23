package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.social.entity.Followers;

public interface FollowersDao extends JpaRepository<Followers, Integer> {

	@Query("SELECT DISTINCT f FROM Followers f LEFT JOIN FETCH f.users WHERE "
			+ "f.id=:id")
	Followers findMemberFollowers(@Param("id") int id);
}
