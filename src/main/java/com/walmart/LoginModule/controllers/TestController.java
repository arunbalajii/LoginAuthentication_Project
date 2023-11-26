package com.walmart.LoginModule.controllers;
import com.walmart.LoginModule.models.Namee;
import com.walmart.LoginModule.models.User;
import com.walmart.LoginModule.payload.response.MessageResponse;
import com.walmart.LoginModule.payload.response.UserInfoResponse;
import com.walmart.LoginModule.security.services.UserDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  //GUEST role user can access this API
  @GetMapping("/guest")
  @PreAuthorize("hasRole('GUEST')")
  public String moderatorAccess() {
    return "Guest Page.";
  }

  //  public Map<String, String> getApiEndpointHeaders(HttpServletRequest request, @RequestHeader Map<String, String> requestHeaders) {
//    // Access headers using HttpServletRequest
//    Enumeration<String> headerNames = request.getHeaderNames();
//    while (headerNames.hasMoreElements()) {
//      String headerName = headerNames.nextElement();
//      String headerValue = request.getHeader(headerName);
//      System.out.println("Header Name: " + headerName + ", Header Value: " + headerValue);
//    }
//
//    // Access headers using @RequestHeader annotation
//    System.out.println("Headers from @RequestHeader annotation: " + requestHeaders);
//
//    // You can return a response or any other logic here
//    return Collections.singletonMap("message", "Headers printed in the console");
//  }
  //ADMIN role user can access this API
  @GetMapping("/verify_admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Page.";
  }

  //API to identify user is logged-in or not
  @GetMapping("/role")
  public ResponseEntity<MessageResponse> role() {
    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

    if (authentication1.getName() != "anonymousUser" && authentication1 != null && authentication1.isAuthenticated()) {
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
