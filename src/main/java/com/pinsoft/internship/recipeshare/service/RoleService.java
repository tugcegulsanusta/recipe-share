package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
}
