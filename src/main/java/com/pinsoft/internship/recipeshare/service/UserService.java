package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.User;
import com.pinsoft.internship.recipeshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User add(User user) {
        return userRepository.save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }
}
