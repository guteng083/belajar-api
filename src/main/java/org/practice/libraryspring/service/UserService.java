package org.practice.libraryspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
