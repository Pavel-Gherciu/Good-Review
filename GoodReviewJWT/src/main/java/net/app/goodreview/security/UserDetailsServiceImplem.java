package net.app.goodreview.security;

import lombok.extern.slf4j.Slf4j;
import net.app.goodreview.model.User;
import net.app.goodreview.security.jwt.JwtUser;
import net.app.goodreview.security.jwt.JwtUserFactory;
import net.app.goodreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImplem implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImplem(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }


        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
