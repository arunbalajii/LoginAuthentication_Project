package com.walmart.LoginModule.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  private ERole name;

  public Role() {

  }

  public ERole getName() {
    return name;
  }

}
