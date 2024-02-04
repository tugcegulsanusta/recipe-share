package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.User;
import com.pinsoft.internship.recipeshare.exceptions.ApiRequestException;
import com.pinsoft.internship.recipeshare.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("The given id is not exist!");
        } else {
            userRepository.findById(id);
        }
    }
    @PreAuthorize("hasAuthority('admin')")
    public void inactivate(Long id){
        if(userRepository.findById(id).isEmpty()){
            throw new ApiRequestException("The given id is not exist");
        }else {
           User user = userRepository.findById(id).get();
           user.setAccountActive(false);
           userRepository.save(user);
        }
    }
    @PreAuthorize("hasAuthority('admin')")
    public void activate(Long id){
        if(userRepository.findById(id).isEmpty()){
            throw new ApiRequestException("The given id is not exist");
        }else {
            User user = userRepository.findById(id).get();
            user.setAccountActive(true);
            userRepository.save(user);
        }
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("The given id is not exist!");
        } else {
            return userRepository.findById(id);
        }

    }
}
