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
@RequestMapping("/member/{id}/mygroups")
public class MemberGroupsController {

	@Autowired
	private UserService userService;
	@Autowired
	private GroupsService groupsService;

	@ModelAttribute("group")
	public Groups getGroups() {
		return new Groups();
	}

	@GetMapping
	public String user(Model model, @PathVariable int id) {
		model.addAttribute("userPage", userService.findOne(id));
		model.addAttribute("userGroups", userService.findMemberGroups(id));
		return "user-mygroups";
	}

	@GetMapping("/exit/{idd}")
	private String delete(@ModelAttribute("group") Groups group,
			@PathVariable int idd) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userService.exitFromGroup(user, group, idd);
		return "redirect:/member/{id}/mygroups";
	}

}
