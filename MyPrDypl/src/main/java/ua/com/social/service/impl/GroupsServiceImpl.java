package ua.com.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.social.dao.GroupsDao;
import ua.com.social.entity.Groups;
import ua.com.social.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsDao groupsDao;
	
	@Override
	public Groups findOne(int id) {
		return groupsDao.findOne(id);
	}

	@Override
	public void save(Groups groups) {
		groupsDao.save(groups);
	}

	@Override
	public Groups findMemberInGroup(int id) {
		return groupsDao.findMemberInGroup(id);
	}

}
