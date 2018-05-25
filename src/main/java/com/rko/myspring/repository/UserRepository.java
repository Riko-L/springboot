package com.rko.myspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.rko.myspring.model.User;
import org.springframework.stereotype.Repository;



public interface UserRepository extends CrudRepository<User, Long>{
    User findByEmail(String email);
}
