package com.example.sportsstore.repository;

import com.example.sportsstore.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
