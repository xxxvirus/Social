package ua.com.social.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.social.entity.User;
import ua.com.social.service.UserService;

public class UserValidator implements Validator {

	private final static Pattern REG = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	private final static Pattern REG2 = Pattern.compile("([0-9]{10})|()");
	private final UserService userService;

	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
				"Can't be empty");
		if (!REG.matcher(user.getEmail()).matches()) {
			errors.rejectValue("email", "", "Enter correct email");
		}
		if (!REG2.matcher(user.getPhoneNumber()).matches()) {
			errors.rejectValue("phoneNumber", "", "Error");
		}
		if (errors.getFieldError("email") == null) {
			if (userService.findByEmail(user.getEmail()) != null) {
				errors.rejectValue("email", "", "Already exist");
			}
		}
	}

}
