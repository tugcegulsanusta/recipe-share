package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
}