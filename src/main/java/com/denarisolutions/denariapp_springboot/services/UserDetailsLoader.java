package com.denarisolutions.denariapp_springboot.services;

import com.denarisolutions.denariapp_springboot.models.User;
import com.denarisolutions.denariapp_springboot.models.UserWithRoles;
import com.denarisolutions.denariapp_springboot.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository userDao;

    public UserDetailsLoader(UserRepository userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        return new UserWithRoles(user);
    }
}
