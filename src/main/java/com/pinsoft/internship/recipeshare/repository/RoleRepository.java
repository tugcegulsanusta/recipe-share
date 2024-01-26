package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameEquals(String name);
}
