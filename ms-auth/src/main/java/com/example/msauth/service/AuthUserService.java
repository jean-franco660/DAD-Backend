package com.example.msauth.service;

import com.example.msauth.Entity.AuthUser;
import com.example.msauth.Entity.TokenDto;
import com.example.msauth.dto.AuthUserDto;

public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);
    public TokenDto login(AuthUserDto authUserDto);
    public TokenDto validate(String token);

}
