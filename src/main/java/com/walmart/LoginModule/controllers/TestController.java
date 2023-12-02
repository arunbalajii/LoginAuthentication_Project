package com.walmart.LoginModule.controllers;
import com.walmart.LoginModule.payload.response.MessageResponse;
import com.walmart.LoginModule.payload.response.UserInfoResponse;
import com.walmart.LoginModule.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
  //GUEST role user can access this API
  @GetMapping("/guest")
  @PreAuthorize("hasRole('GUEST')")
  public String moderatorAccess() {
    logger.info("Guest: In Guest Page !");
    return "Guest Page.";
  }

  //ADMIN role user can access this API
  @GetMapping("/verify_admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {

    logger.info("Admin: In Admin Page !");
    return "Admin Page.";
  }

  //API to identify user is logged-in or not
  @GetMapping("/role")
  public ResponseEntity<MessageResponse> role() {
    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

    if (authentication1.getName() != "anonymousUser" && authentication1 != null && authentication1.isAuthenticated()) {
      logger.info("Role: In Role API !");
      return ResponseEntity.ok().body(new MessageResponse(String.valueOf(authentication1.getAuthorities())));
    } else {
      return ResponseEntity.ok().body(new MessageResponse("Not Logged in!!"));
    }

  }

  @GetMapping("/profile")
  public ResponseEntity<?> profile(Authentication authentication) {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication.getName() != "anonymousUser" && authentication != null && authentication.isAuthenticated()){
//      return ResponseEntity.ok().body(new MessageResponse("Welcome, "+authentication.getName()+"!!"));
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//    Namee namee = new Namee();
//      User user = (User) authentication.getPrincipal();
//    return ResponseEntity.ok(new UserInfoResponse(userDetails.getUsername(),
//            userDetails.getEmail()));
//    }
//    else {
//      return ResponseEntity.ok().body(new MessageResponse("Please Login to check your details"));

//    }
    return ResponseEntity.ok()
            .body(new UserInfoResponse(
                    userDetails.getUsername(),
                    userDetails.getEmail()/*,
                    userDetails.getName()*/));
  }
}
