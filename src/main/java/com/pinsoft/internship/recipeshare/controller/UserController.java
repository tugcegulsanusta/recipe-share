package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.dto.AuthenticationRequest;
import com.pinsoft.internship.recipeshare.dto.AuthenticationResponse;
import com.pinsoft.internship.recipeshare.dto.RegisterRequest;
import com.pinsoft.internship.recipeshare.service.AuthenticationService;
import com.pinsoft.internship.recipeshare.service.UserService;
import com.pinsoft.internship.recipeshare.entity.User;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationService authService;
    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/user_account")
    public Collection<User> get(){
        return userService.getAll();
    }
    @GetMapping("/user_account/{id}")
    public User get(@PathVariable Long id){
        Optional<User> optional = userService.getById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/inactivate/{id}")
    public void inactivate(@PathVariable Long id){
        Optional<User> optional = userService.getById(id);
        if(optional.isPresent()){
            userService.inactivate(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/activate/{id}")
    public void activate(@PathVariable Long id){
        Optional<User> optional = userService.getById(id);
        if(optional.isPresent()){
            userService.activate(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
