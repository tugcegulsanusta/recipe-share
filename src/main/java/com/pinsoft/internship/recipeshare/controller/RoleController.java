package com.pinsoft.internship.recipeshare.controller;

import com.pinsoft.internship.recipeshare.entity.Role;
import com.pinsoft.internship.recipeshare.service.RoleService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/role")
    @PermitAll
    public Collection<Role> get(){return roleService.getAll();}
    @GetMapping("/role/{id}")
    @PermitAll
    public Role get(@PathVariable Long id){
        Optional<Role> optional = roleService.getById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/role/{id}")
    public void delete(@PathVariable Long id){
        roleService.delete(id);
    }
    @PostMapping("/role")
    public Role add(@RequestBody Role role){
        return roleService.add(role);
    }
}
