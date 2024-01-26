package com.pinsoft.internship.recipeshare.service;

import com.pinsoft.internship.recipeshare.entity.Role;
import com.pinsoft.internship.recipeshare.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role add(Role role) {return roleRepository.save(role);}
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
    public Optional<Role> getById(Long id){
        return roleRepository.findById(id);
    }

}
