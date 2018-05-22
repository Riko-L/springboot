package com.rko.myspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.rko.myspring.model.User;


//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long>{

}
