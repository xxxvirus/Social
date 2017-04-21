package ua.com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.social.entity.Groups;
import ua.com.social.entity.User;
import ua.com.social.service.GroupsService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/group/{id}")
public class GroupController {

	@Autowired
	private GroupsService groupsService;
	@Autowired
	private UserService userService;
	
	@GetMapping
	private String group(Model model, @PathVariable int id){
		model.addAttribute("groupName", groupsService.findOne(id));
		model.addAttribute("group", groupsService.findMemberInGroup(id));
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
}
