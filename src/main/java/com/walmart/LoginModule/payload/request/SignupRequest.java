package com.walmart.LoginModule.payload.request;

import com.walmart.LoginModule.models.Address;
import com.walmart.LoginModule.models.Namee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> roles;

  @NotBlank
  private String gender;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @NotBlank
  private String phone;

  public Address address;

  public Namee name;

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



  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


}
