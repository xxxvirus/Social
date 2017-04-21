package ua.com.social.service;

import ua.com.social.entity.Groups;

public interface GroupsService {

	Groups findOne(int id);
	void save(Groups groups);
	
	Groups findMemberInGroup(int id);
	
}
