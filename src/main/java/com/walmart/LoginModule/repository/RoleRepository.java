package com.walmart.LoginModule.repository;

import com.walmart.LoginModule.models.ERole;
import com.walmart.LoginModule.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
