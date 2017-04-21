package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.social.entity.Groups;

public interface GroupsDao extends JpaRepository<Groups, Integer> {

	@Query("SELECT DISTINCT g FROM Groups g LEFT JOIN FETCH g.users WHERE "
			+ "g.id=:id")
	Groups findMemberInGroup(@Param("id") int id);
}
