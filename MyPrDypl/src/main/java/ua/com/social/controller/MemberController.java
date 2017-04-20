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
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.social.algoritm.AES;
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
	
	@GetMapping
	public String user(Model model, @PathVariable int id){
		model.addAttribute("users", userService.findOne(id));
		model.addAttribute("posts", postService.findByUserId(id));
		model.addAttribute("friends", userService.findMemberFriends(id));
		return "user-member";
	}
	
	@GetMapping("/delete/{idd}")
	public String delete(@PathVariable int idd) {
		postService.delete(idd);
		return "redirect:/member/{id}";
	}
	
	@PostMapping
	public String addPost(@ModelAttribute("post") Post post, @RequestParam("key") String  key, @PathVariable int id){
		User user = userService.findOne(id);
		post.setUser(user);
		String text = post.getText();
		String aesT = aes.encrypt(key, text);
		post.setText(aesT);
		postService.save(post);
		return "redirect:/member/{id}";
	}
	
	@GetMapping("/addToFriend")
	private String addToFriend(@ModelAttribute("friend") Friends friend, @PathVariable int id){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.addFriend(user, friend, id);
		return "redirect:/member/{id}";
	}
	
}
