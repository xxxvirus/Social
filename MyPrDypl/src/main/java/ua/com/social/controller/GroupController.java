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

import ua.com.social.algoritm.RSA;
import ua.com.social.entity.Groups;
import ua.com.social.entity.Post;
import ua.com.social.entity.User;
import ua.com.social.service.GroupsService;
import ua.com.social.service.PostService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/group/{id}")
public class GroupController {

	@Autowired
	private GroupsService groupsService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
	@ModelAttribute("post")
	public Post getPost() {
		return new Post();
	}
	
	@GetMapping
	private String group(Model model, @PathVariable int id, String decText){
		model.addAttribute("groupName", groupsService.findOne(id));
		model.addAttribute("group", groupsService.findMemberInGroup(id));
		model.addAttribute("posts", postService.findByGroupId(id));
		model.addAttribute("isAtGroup", groupsService.memberAtGroup(id));
		model.addAttribute("decText", decText);
		return "user-group";
	}
	
	@GetMapping("/follow")
	private String follow(@ModelAttribute("group") Groups group,
			@PathVariable int id){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.followGroup(user, group, id);
		return "redirect:/group/{id}";
	}
	
	@GetMapping("/exit")
	private String delete(@ModelAttribute("group") Groups group,
			@PathVariable int id) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.exitFromGroup(user, group, id);
		return "redirect:/group/{id}";
	}
	
	@PostMapping
	public String addPost(@ModelAttribute("post") Post post, @PathVariable int id){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		post.setUser(user);
		Groups group = groupsService.findOne(id);
		post.setGroups(group);
		String text = post.getText();
		String encT = RSA.encrypt(text, group.getKeys().getPublickKey());
		post.setText(encT);
		postService.save(post);
		return "redirect:/group/{id}";
	}
	
	@GetMapping("/dec/{idd}")
	public String decrypt(Model model, @PathVariable int id, @PathVariable int idd){
		Groups group = groupsService.findOne(id);
		Post post = postService.findOne(idd);
		String text = post.getText();
		String decText = RSA.decrypt(text, group.getKeys().getPrivateKey());
		return group(model, id, decText);
	}
	
	@GetMapping("/delete/{idd}")
	public String delete(@PathVariable int idd) {
		postService.delete(idd);
		return "redirect:/group/{id}";
	}
}
