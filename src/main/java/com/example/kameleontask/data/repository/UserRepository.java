package com.example.kameleontask.data.repository;

import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.util.logging.annotation.LoggableDbQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @LoggableDbQuery(description = "Search for user by email or username")
    Optional<User> findByEmailOrUsername(String email, String username);

    @LoggableDbQuery(description = "Save the user")
    @Override
    <S extends User> S save(S user);

    @LoggableDbQuery(description = "Find the user by id")
    @Override
    Optional<User> findById(Long id);
}
