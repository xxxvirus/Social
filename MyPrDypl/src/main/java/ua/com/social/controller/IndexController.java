package ua.com.social.controller;

import java.security.KeyPair;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.social.algoritm.AES;
import ua.com.social.algoritm.RSA;
import ua.com.social.entity.Groups;
import ua.com.social.entity.RSAKeys;
import ua.com.social.entity.RSAKeysUser;
import ua.com.social.entity.User;
import ua.com.social.service.GroupsService;
import ua.com.social.service.PostService;
import ua.com.social.service.RSAKeysService;
import ua.com.social.service.RSAKeysUserService;
import ua.com.social.service.UserService;
import ua.com.social.validator.UserValidator;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	private AES aes = new AES();
	@Autowired
	private GroupsService groupsService;
	@Autowired
	private RSAKeysService rsakeyService;
	@Autowired
	private RSAKeysUserService keyService;
	
	@ModelAttribute("group")
	public Groups getGroups() {
		return new Groups();
	}
	
	@GetMapping
	public String index(Principal principal, Model model){
		if(principal!=null){
			System.out.println(principal.getName());
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return "user-index";
	}
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user, BindingResult br, Model model){
		if (br.hasErrors())
			return "user-registration";
		userService.save(user);
		RSAKeysUser rsa = new RSAKeysUser();
		KeyPair pair = RSA.generateKeyPair();
	    PublicKey pubKey = pair.getPublic();
	    PrivateKey privKey = pair.getPrivate();
	    rsa.setPublickKey(pubKey);
	    rsa.setPrivateKey(privKey);
	    rsa.setUser(user);
	    keyService.save(rsa);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "user-login";
	}
	
	@RequestMapping("/decrypt")
	public String decrypt(Model model, String resultText){
		model.addAttribute("text", resultText);
		return "user-decrypt";
	}
	
	@RequestMapping("/encrypt")
	public String encrypt(Model model, String resultText){
		model.addAttribute("text", resultText);
		return "user-encrypt";
	}
	
	@GetMapping(value="/decrypt/dec")
	public String decryptM(Model model, @RequestParam("key") String key, @RequestParam("value") String value){
		String resultText = aes.decrypt(key, value);
		return decrypt(model, resultText);
	}
	
	@GetMapping(value="/encrypt/enc")
	public String encryptM(Model model, @RequestParam("key") String key, @RequestParam("value") String value){
		String resultText = aes.encrypt(key, value);
		return encrypt(model, resultText);
	}
	
	@RequestMapping("/createGroup")
	public String creteG(Model model){
		model.addAttribute("group", new Groups());
		return "user-createGroup";
	}
	
	@PostMapping("/createGroup")
	public String saveG(@ModelAttribute("group") Groups group){
	    groupsService.save(group);
	    RSAKeys rsa = new RSAKeys();
		KeyPair pair = RSA.generateKeyPair();
	    PublicKey pubKey = pair.getPublic();
	    PrivateKey privKey = pair.getPrivate();
	    rsa.setPublickKey(pubKey);
	    rsa.setPrivateKey(privKey);
	    rsa.setGroups(group);
	    rsakeyService.save(rsa);
		return "user-index";
	}
	
}
