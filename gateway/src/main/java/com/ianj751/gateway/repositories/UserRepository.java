package com.ianj751.gateway.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ianj751.gateway.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUsernameAndPassword(String username, String password);

    public Optional<User> findByUsername(String username);
}