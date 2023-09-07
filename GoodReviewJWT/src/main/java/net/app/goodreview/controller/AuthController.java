package net.app.goodreview.controller;

import net.app.goodreview.dto.request.AuthRequestDto;
import net.app.goodreview.dto.request.RegisterRequestDto;
import net.app.goodreview.dto.UserDto;
import net.app.goodreview.model.User;
import net.app.goodreview.security.jwt.JwtAuthenticationException;
import net.app.goodreview.security.jwt.JwtTokenProvider;
import net.app.goodreview.service.UserService;
import net.app.goodreview.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static net.app.goodreview.dto.UserDto.fromUser;

@RestController
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private UserValidator userValidator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserService userService, UserValidator userValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDto requestDto) {
        Map<Object, Object> response = new HashMap<>();

        try {
            String username = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByEmail(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoleList());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto requestDto, BindingResult result) {
        Map<Object, Object> response = new HashMap<>();
        userValidator.validate(requestDto, result);

        Map<String, String> errorCodes = new HashMap<>();

        if(result.hasErrors()){
            response.put("message", "Failed register because of fields");

            result.getFieldErrors()
                    .stream()
                    .forEach(f -> errorCodes.put(f.getField(), f.getCode()));
            response.put("errors", errorCodes);
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(response);
        }

        userService.save(requestDto.toUser());

        try {
            String username = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByEmail(username);

            String token = jwtTokenProvider.createToken(username, user.getRoleList());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.put("message", "Failed autoLogin to system");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(response);
        }
    }

    @CrossOrigin
    @GetMapping("/login/is-verify")
    public ResponseEntity isValidToken(String token) {
        Map<Object, Object> response = new HashMap<>();

        try {
            JwtTokenProvider jwtProvider = new JwtTokenProvider();
            jwtProvider.validateToken(token);

            response.put("message", "Token is valid");
            return ResponseEntity.ok(response);
        } catch (JwtAuthenticationException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping(value = "/dashboard")
    @ResponseBody
    public ResponseEntity<Object> getUserByToken(HttpServletRequest request){
        Map<Object, Object> response = new HashMap<>();
        Principal principal = request.getUserPrincipal();

        User user = userService.findByLogin(principal.getName());

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


