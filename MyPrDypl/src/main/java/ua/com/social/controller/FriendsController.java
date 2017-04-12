package ua.com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.social.service.FriendsService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/member/{id}/friends")
public class FriendsController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendsService friendsService;
	
	@GetMapping
	public String user(Model model, @PathVariable int id){
		model.addAttribute("friends", friendsService.findMemberFriends(id));
		return "user-friends";
	}
}
