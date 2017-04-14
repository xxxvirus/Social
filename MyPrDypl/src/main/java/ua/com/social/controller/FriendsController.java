package ua.com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.social.entity.Friends;
import ua.com.social.entity.User;
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
		model.addAttribute("userPage", userService.findOne(id));
		model.addAttribute("friends", friendsService.findMemberFriends(id));
		return "user-friends";
	}
	
	@GetMapping("/confirm/{idd}")
	private String addToFriend(@ModelAttribute("friend") Friends friend, @PathVariable int idd){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.addFriend(user, friend, idd);
		return "redirect:/member/{id}/friends";
	}
	
	@GetMapping("/delete/{idd}")
	private String delete(@ModelAttribute("friend") Friends friend, @PathVariable int idd){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.removeFriend(user, friend, idd);
		return "redirect:/member/{id}/friends";
	}
}
