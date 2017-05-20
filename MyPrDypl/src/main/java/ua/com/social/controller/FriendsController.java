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
import ua.com.social.service.PostService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/member/{id}/friends")
public class FriendsController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private PostService postService;
//	private AES aes = new AES();

	@GetMapping
	public String user(Model model, @PathVariable int id) {
		model.addAttribute("userPage", userService.findOne(id));
		model.addAttribute("friends", friendsService.findMemberFriends(id));
		model.addAttribute("isFriend", friendsService.isMyFriend(id));
		return "user-friends";
	}

	@GetMapping("/confirm/{idd}")
	private String addToFriend(Model model, @ModelAttribute("friend") Friends friend,
			@PathVariable int idd) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.addFriend(user, friend, idd);
		return "redirect:/member/{id}/friends";
	}

	@GetMapping("/delete/{idd}")
	private String delete(@ModelAttribute("friend") Friends friend,
			@PathVariable int idd) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.removeFriend(user, friend, idd);
		return "redirect:/member/{id}/friends";
	}

//	@RequestMapping("/viewPost/{idd}")
//	private String view(Model model, @PathVariable int idd, String text) {
//		model.addAttribute("posts", postService.findByUserId(idd));
//		model.addAttribute("encrypt", text);
//		return "user-viewPost";
//	}
//
//	@GetMapping("/viewPost/{idd}/dec/{postId}")
//	public String decryptM(Model model, @RequestParam("key") String key,
//			@RequestParam("value") String value, @PathVariable int idd, @PathVariable int postId) {
//		String text = aes.decrypt(key, value);
//		return view(model, idd, text);
//	}
}
