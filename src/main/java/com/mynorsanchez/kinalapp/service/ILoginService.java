package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.LoginUser;

public interface ILoginService {
    LoginUser authenticate(String email, String password);
    LoginUser register(LoginUser user);
    LoginUser findByEmail(String email);
}