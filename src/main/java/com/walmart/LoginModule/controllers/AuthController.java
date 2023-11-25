package com.walmart.LoginModule.controllers;

import com.walmart.LoginModule.models.ERole;
import com.walmart.LoginModule.models.Role;
import com.walmart.LoginModule.models.User;
import com.walmart.LoginModule.payload.request.LoginRequest;
import com.walmart.LoginModule.payload.request.SignupRequest;
import com.walmart.LoginModule.payload.response.MessageResponse;
import com.walmart.LoginModule.payload.response.UserInfoResponse;
import com.walmart.LoginModule.repository.RoleRepository;
import com.walmart.LoginModule.repository.UserRepository;
import com.walmart.LoginModule.security.jwt.AuthEntryPointJwt;
import com.walmart.LoginModule.security.jwt.JwtUtils;
import com.walmart.LoginModule.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  //private String amount = "0";

// ************ Capstone project ************

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())); //getUsername

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new MessageResponse("Login successful !!"));
//            .body(new UserInfoResponse(userDetails.getId(),
//                   userDetails.getUsername(),
//                    userDetails.getEmail(),
//                    roles));
  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    if (userRepository.existsByPhone(signUpRequest.getPhone())){
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Phone number already in use!"));
    }


    //SignupRequest.Address address1 = new SignupRequest.Address();
    // Create new user's account
    //public String amount;
//    User user = new User(signUpRequest.getUsername(),
//            signUpRequest.getEmail(),
//            encoder.encode(signUpRequest.getPassword()),
//            signUpRequest.getNamee(),
//            signUpRequest.getGender(),
//            signUpRequest.getPhone(),
//            signUpRequest.getAddress(),
//            "0");
        User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getNamee(),
            signUpRequest.getGender(),
            signUpRequest.getPhone(),
            signUpRequest.getAddress());



    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    /*if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {*/
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "guest":
            Role modRole = roleRepository.findByName(ERole.ROLE_GUEST)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    //}

    user.setRoles(roles);
    user.setValidated("NO");
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully !!!"));
  }
}
