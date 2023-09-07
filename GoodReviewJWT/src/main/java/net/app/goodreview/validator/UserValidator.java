package net.app.goodreview.validator;

import net.app.goodreview.dto.request.RegisterRequestDto;
import net.app.goodreview.model.User;
import net.app.goodreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    public static final String REQUIRED = "Acest câmp trebuie completat.";
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequestDto user = (RegisterRequestDto)target;
        String regexPattern = "^(.+)@(\\S+)$";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", REQUIRED);
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Loginul trebuie sa fie peste 3 caractere.");
        }

        if(userService.findByLogin(user.getUsername()) != null){
            errors.rejectValue("username", "Acest utilizator deja exista.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", REQUIRED);
        if(Pattern.compile(regexPattern)
                .matcher(user.getEmail())
                .matches() == false){
            errors.rejectValue("email", "Acest camp trebuie sa fie completat cu email!");
        }

        if(userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Account cu acest email deja exista.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", REQUIRED);
        if(user.getFirstName().length() < 2){
            errors.rejectValue("firstName", "Prenumele trebuie sa fie peste 2 caractere.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", REQUIRED);
        if(user.getLastName().length() < 2){
            errors.rejectValue("lastName", "Numele trebuie sa fie peste 2 caractere.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", REQUIRED);
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Parola trebuie sa fie peste 8 caractere si mai putin de 32 de caractere.");
        }
    }
}
