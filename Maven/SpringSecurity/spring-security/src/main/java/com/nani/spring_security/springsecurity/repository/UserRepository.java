package com.nani.spring_security.springsecurity.repository;

import com.nani.spring_security.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


//    Optional<User> findByName(String username);


}
