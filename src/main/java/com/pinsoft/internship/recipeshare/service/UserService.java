package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
}
