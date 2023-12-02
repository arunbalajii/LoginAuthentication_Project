package com.walmart.LoginModule.payload.response;

import com.walmart.LoginModule.models.Namee;

import java.util.List;

public class UserInfoResponse {
//    private String id;
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
        private List<String> roles;

    public UserInfoResponse( /*String id,*/String username, String email/*,Namee name*/, List<String> roles) {
       /* this.id = id;*/
        this.username = username;
        this.email = email;
//        this.name = name;
        this.roles = roles;
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

    public List<String> getRoles() {
        return roles;
    }
}
