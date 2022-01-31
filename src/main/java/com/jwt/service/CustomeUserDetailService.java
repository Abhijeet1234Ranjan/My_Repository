package com.jwt.service;

import com.card.constants.ServiceConstants;
import com.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Environment environment;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("Abhi")){
            return new org.springframework.security.core.userdetails.User("Abhi","abhi123",new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User Not Found !!");
        }
    }

    public User submitUser(User user){
        return userRepository.save(user);
    }
}
