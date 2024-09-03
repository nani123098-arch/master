package com.nani.spring_security.springsecurity.repository;

import com.nani.spring_security.springsecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
