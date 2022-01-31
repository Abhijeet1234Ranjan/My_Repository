package com.jwt.controller;

import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.model.User;
import com.jwt.service.CustomeUserDetailService;
import com.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomeUserDetailService customeUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new BadCredentialsException("Bad Credentials");
        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new BadCredentialsException("Bad Credentials");
        }

        UserDetails userDetails = this.customeUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT token " +token);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User submitUser(@RequestBody User user){
       return customeUserDetailService.submitUser(user);
    }

}
