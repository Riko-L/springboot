package com.rko.myspring.repository;

import com.rko.myspring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
