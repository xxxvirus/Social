package ua.com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.social.algoritm.AES;
import ua.com.social.algoritm.RSA;
import ua.com.social.entity.Followers;
import ua.com.social.entity.Friends;
import ua.com.social.entity.Post;
import ua.com.social.entity.User;
import ua.com.social.service.FriendsService;
import ua.com.social.service.PostService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/member/{id}")
public class MemberController {

	@Autowired
	private UserService userService;
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private PostService postService;
	private AES aes = new AES();

	@ModelAttribute("post")
	public Post getPost() {
		return new Post();
	}

	@ModelAttribute("friend")
	public Friends getFriends() {
		return new Friends();
	}

	@ModelAttribute("friend")
	public Followers getFollowers() {
		return new Followers();
	}

	@GetMapping
	public String user(Model model, @PathVariable int id, String decText) {
		model.addAttribute("users", userService.findOne(id));
		model.addAttribute("posts", postService.findByUserId(id));
		model.addAttribute("isFriend", userService.isAfriend(id));
		model.addAttribute("isFollower", userService.isAfollower(id));
		model.addAttribute("isFollowerRequested", userService.isRequested(id));
		model.addAttribute("decText", decText);
		model.addAttribute(
				"name",
				aes.decrypt(RSA.decrypt(userService.findOne(id).getKeyAes()
						.getGenKey(), userService.findOne(id).getKeys()
						.getPrivateKey()), userService.findOne(id).getName()));
		model.addAttribute("surname", aes.decrypt(RSA.decrypt(userService
				.findOne(id).getKeyAes().getGenKey(), userService.findOne(id)
				.getKeys().getPrivateKey()), userService.findOne(id)
				.getSurname()));
		model.addAttribute("country", aes.decrypt(RSA.decrypt(userService
				.findOne(id).getKeyAes().getGenKey(), userService.findOne(id)
				.getKeys().getPrivateKey()), userService.findOne(id)
				.getCountry()));
		model.addAttribute(
				"city",
				aes.decrypt(RSA.decrypt(userService.findOne(id).getKeyAes()
						.getGenKey(), userService.findOne(id).getKeys()
						.getPrivateKey()), userService.findOne(id).getCity()));
		model.addAttribute("phoneNumber", aes.decrypt(RSA.decrypt(userService
				.findOne(id).getKeyAes().getGenKey(), userService.findOne(id)
				.getKeys().getPrivateKey()), userService.findOne(id)
				.getPhoneNumber()));
		return "user-member";
	}

	@GetMapping("/deleteP/{idd}")
	public String delete(@PathVariable int idd) {
		postService.delete(idd);
		return "redirect:/member/{id}";
	}

	@PostMapping
	public String addPost(@ModelAttribute("post") Post post,
			@PathVariable int id) {
		User user = userService.findOne(id);
		post.setUser(user);
		String text = post.getText();
		String encT = RSA.encrypt(text, user.getKeys().getPublickKey());
		post.setText(encT);
		postService.save(post);
		return "redirect:/member/{id}";
	}

	@GetMapping("/dec/{idd}")
	public String decrypt(Model model, @PathVariable int id,
			@PathVariable int idd) {
		User user = userService.findOne(id);
		Post post = postService.findOne(idd);
		String text = post.getText();
		String decText = RSA.decrypt(text, user.getKeys().getPrivateKey());
		return user(model, id, decText);
	}

	@GetMapping("/follow")
	private String followFriend(@ModelAttribute("follower") Followers follower,
			@PathVariable int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.follow(user, follower, id);
		return "redirect:/member/{id}";
	}

	@GetMapping("/deleteRequest")
	private String delRequest(@PathVariable int id) {
		userService.deleteRequest(id);
		return "redirect:/member/{id}";
	}

	@GetMapping("/delete")
	private String delete(@ModelAttribute("friend") Friends friend,
			@PathVariable int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.removeFriend(user, friend, id);
		return "redirect:/member/{id}";
	}

	@GetMapping("/confirm")
	private String addToFriend(Model model,
			@ModelAttribute("friend") Friends friend, @PathVariable int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.addFriend(user, friend, id);
		return "redirect:/member/{id}";
	}

}
