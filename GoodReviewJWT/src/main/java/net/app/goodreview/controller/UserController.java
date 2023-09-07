package net.app.goodreview.controller;

import net.app.goodreview.dto.UserDto;
import net.app.goodreview.dto.request.UpdateUserDto;
import net.app.goodreview.dto.responses.CompaniesResponseDto;
import net.app.goodreview.model.User;
import net.app.goodreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.app.goodreview.dto.UserDto.fromUser;
import static net.app.goodreview.service.mapper.UserMapper.toUser;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return fromUser(userService.update(toUser(updateUserDto)));
    }
}
