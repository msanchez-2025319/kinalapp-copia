package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.LoginUser;
import com.mynorsanchez.kinalapp.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Override
    public LoginUser authenticate(String email, String password) {
        LoginUser user = loginUserRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password) && user.isActive()) {
            return user;
        }
        return null;
    }

    @Override
    public LoginUser register(LoginUser user) {
        if (loginUserRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return loginUserRepository.save(user);
    }

    @Override
    public LoginUser findByEmail(String email) {
        return loginUserRepository.findByEmail(email);
    }
}