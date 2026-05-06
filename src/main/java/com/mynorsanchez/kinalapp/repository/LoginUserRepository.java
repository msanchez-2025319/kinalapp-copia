package com.mynorsanchez.kinalapp.repository;

import com.mynorsanchez.kinalapp.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findByEmail(String email);
    boolean existsByEmail(String email);
}