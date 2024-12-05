package com.alice.api.controller;

import com.alice.api.config.security.JwtService;
import com.alice.api.dto.LoginDTO;
import com.alice.api.service.iplm.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserController {

  @Autowired
  private UserInfoService service;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public String authenticateAndGetToken(@RequestBody LoginDTO authRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
            authRequest.getPassword())
    );
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(authRequest.getEmail());
    } else {
      throw new RuntimeException("User not found!");
    }
  }
}
