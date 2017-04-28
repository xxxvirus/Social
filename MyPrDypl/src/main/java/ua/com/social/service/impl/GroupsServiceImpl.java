package ua.com.social.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.com.social.dao.GroupsDao;
import ua.com.social.entity.Groups;
import ua.com.social.entity.User;
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

	@Override
	public boolean memberAtGroup(int id) {
		Groups group = groupsDao.findMemberInGroup(id);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		int userId = user.getId();
		List<User> list = group.getUsers();
		for (User user2 : list) {
			if(user2.getId()==userId){
				return true;
			}
		}
		return false;
	}

}
