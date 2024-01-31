package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.dto.AuthenticationRequest;
import com.pinsoft.internship.recipeshare.dto.AuthenticationResponse;
import com.pinsoft.internship.recipeshare.dto.RegisterRequest;
import com.pinsoft.internship.recipeshare.entity.Role;
import com.pinsoft.internship.recipeshare.entity.User;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.RoleRepository;
import com.pinsoft.internship.recipeshare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        if(request.getUsername().isEmpty()||request.getEmail().isEmpty()){
            throw new ApiRequestException("username/email cannot be empty!");
        }else{
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            Role role = roleRepository.findByNameEquals("user").get(0);
            user.setRole(role);
            User savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateToken(savedUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user =userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
}
