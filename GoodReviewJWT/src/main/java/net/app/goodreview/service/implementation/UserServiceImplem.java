package net.app.goodreview.service.implementation;

import lombok.extern.slf4j.Slf4j;
import net.app.goodreview.model.*;
import net.app.goodreview.repository.RoleRepository;
import net.app.goodreview.repository.UserRepository;
import net.app.goodreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImplem implements UserService {

    private UserRepository userRepo;

    private RoleRepository roleRepo;

    private BCryptPasswordEncoder bcrEncoder;

    @Autowired
    public UserServiceImplem(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bcrEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bcrEncoder = bcrEncoder;
    }

    @Override
    public User save(User user) {
        Role role = roleRepo.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setPassword(bcrEncoder.encode(user.getPassword()));
        user.setRoleList(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepo.save(user);

        //log.info("In registered - user: {} successfully registered", registeredUser.toString());
        return registeredUser;
    }

    @Override
    public List<User> getAll(){
        List<User> result = userRepo.findAll();
        return result;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByLogin(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public User findById(Long id){
        User result = userRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public void delete(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public User update(User user) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = userDetails.getUsername();
        User userToUpdate = findByLogin(login);

        if(userToUpdate == null){
            throw new UsernameNotFoundException("The user " + login + " was not found");
        }

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setIcon(user.getIcon());

        return userRepo.save(userToUpdate);
    }

}
