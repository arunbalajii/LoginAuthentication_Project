package com.walmart.LoginModule.security.services;

import com.walmart.LoginModule.models.User;
import com.walmart.LoginModule.repository.UserRepository;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //username
    User user = userRepository.findByEmail(email) //username
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

    return (UserDetails) UserDetailsImpl.build(user);
  }

}
