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
import ua.com.social.service.FollowersService;
import ua.com.social.service.FriendsService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/member/{id}/followers")
public class FollowersController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private FollowersService followersService;
	
	@GetMapping
	public String user(Model model, @PathVariable int id) {
		model.addAttribute("userPage", userService.findOne(id));
		model.addAttribute("followers", followersService.findMemberFollowers(id));
		return "user-followers";
	}
	
	@GetMapping("/confirm/{idd}")
	private String addToFriend(Model model, @ModelAttribute("friend") Friends friend,
			@PathVariable int idd) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.addFriend(user, friend, idd);
		return "redirect:/member/{id}/followers";
	}
}
