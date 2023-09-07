package net.app.goodreview.dto.request;

import lombok.Data;
import net.app.goodreview.model.User;

@Data
public class RegisterRequestDto {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String repeatPassword;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

}
