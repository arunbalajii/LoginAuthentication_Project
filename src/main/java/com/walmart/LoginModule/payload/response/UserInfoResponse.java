package com.walmart.LoginModule.payload.response;

import com.walmart.LoginModule.models.Namee;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.List;

public class UserInfoResponse {
    private Integer userId;
    private String username;
    private String email;

//    private Namee name;
//
//    public Namee getName() {
//        return name;
//    }
//
//    public void setName(Namee name) {
//        this.name = name;
//    }
//        private List<String> roles;

    public UserInfoResponse(Integer userId, String username, String email/*,Namee name*//*, List<String> roles*/) {
        this.userId = userId;
        this.username = username;
        this.email = email;
//        this.name = name;
//        this.roles = roles;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  /*  public List<String> getRoles() {
        return roles;
    }*/

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
