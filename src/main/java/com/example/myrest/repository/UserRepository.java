package com.example.myrest.repository;

import com.example.myrest.model.TB_User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<TB_User, Long> {
    @EntityGraph(attributePaths = {"boards"})
    List<TB_User> findAll();
    TB_User findByUsername(String username);
    String existsByUsername(String username);
}
