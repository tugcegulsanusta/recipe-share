package com.pinsoft.internship.recipeshare.repository;

import com.pinsoft.internship.recipeshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}
