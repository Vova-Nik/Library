package com.nikollenko.library.dao;


import com.nikollenko.library.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
