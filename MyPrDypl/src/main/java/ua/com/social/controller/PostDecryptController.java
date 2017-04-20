package ua.com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.social.algoritm.AES;
import ua.com.social.service.FriendsService;
import ua.com.social.service.PostService;
import ua.com.social.service.UserService;

@Controller
@RequestMapping("/decrypt")
//@SessionAttributes("decryptor")
public class PostDecryptController {

	private AES aes = new AES();
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	
//	@InitBinder("decryptor")
//	protected void bind(WebDataBinder binder){
//		binder.registerCustomEditor(Friends.class, new FriendEditor(friendsService));
//	}
	
//	@GetMapping
//	public String decrypt(Model model, String encrupt, @PathVariable int id){
//		model.addAttribute("users", userService.findOne(id));
//		model.addAttribute("friends", friendsService.findMemberFriends(id));
//		
////		model.addAttribute("encrypt", encrupt);
//		return "user-decryptor";
//	}
//	
//	@GetMapping("/findPost/{idd}")
//	public String findPost(Model model, String encrupt, @PathVariable int id, @PathVariable int idd){
//		model.addAttribute("friend", userService.findOne(id));
//		model.addAttribute("posts", postService.findByUserId(idd));
//		return decrypt(model, encrupt, id);
//	}
	
	@GetMapping
	public String view(Model model, String dec){
		model.addAttribute("decText", dec);
		return "user-decrypt";
	}
	
	@GetMapping(value="/dec")
	public String decryptM(Model model, @RequestParam("key") String key, @RequestParam("value") String value){
		String dec = aes.decrypt(key, value);
		return view(model, dec);
	}
	
}
