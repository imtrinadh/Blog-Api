package com.example.blog.repository;

import com.example.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
