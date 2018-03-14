package com.audi.interview.booking.jpa.repository;

import com.audi.interview.booking.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * For Spring Data JPA query methods see:
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}