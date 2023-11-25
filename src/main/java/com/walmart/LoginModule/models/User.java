package com.walmart.LoginModule.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.HashSet;
import java.util.Set;

@Document(collection = "User_List")
//@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;


//  @NotBlank
//  @Size(max = 20)
//  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  public String gender;

  public String phone;

  public String validated;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public Address address;

  //public String amount;
  public Namee name;

//  public User(String username, String email, String password,Namee name, String gender, String phone, Address address, String amount) {
//    this.username = username;
//    this.email = email;
//    this.password = password;
//    this.name = name;
//    this.gender = gender;
//    this.phone = phone;
//    this.address = address;
//    this.amount = amount;
//  }

  public User(String username, String email, String password,Namee name, String gender, String phone, Address address) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.name = name;
    this.gender = gender;
    this.phone = phone;
    this.address = address;
  }

  public String getValidated() {
    return validated;
  }

  public void setValidated(String validated) {
    this.validated = validated;
  }

  public Namee getNamee() {
    return name;
  }

  public void setNamee(Namee name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public String getGender(){return gender;}

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}


