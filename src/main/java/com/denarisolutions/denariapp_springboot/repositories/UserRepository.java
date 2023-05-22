package com.denarisolutions.denariapp_springboot.repositories;

import com.denarisolutions.denariapp_springboot.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findById(long id);
        User findByUsername(String username);
}



