package com.example.msauth.service.Impl;

import com.example.msauth.Entity.AuthUser;
import com.example.msauth.Entity.TokenDto;
import com.example.msauth.Repository.AuthRepository;
import com.example.msauth.Security.JwtProvider;
import com.example.msauth.dto.AuthUserDto;
import com.example.msauth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;


    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        Optional<AuthUser> user = authRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent())
            return null;
        String password = passwordEncoder.encode(authUserDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();
        return authRepository.save(authUser);
    }

    @Override
    public TokenDto login(AuthUserDto authUserDto) {
            Optional<AuthUser> user = authRepository.findByUserName(authUserDto.getUserName());
            if (!user.isPresent())
                return null;
            if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
                return new TokenDto(jwtProvider.createToken(user.get()));
            return null;
        }

    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validate(token)) {
            return null;
        }
        String Username = jwtProvider.getUserNameFromToken(token);
        if (!authRepository.findByUserName(username).isPresent()) {
            return null;
        }
        return new TokenDto(token);
    }
}
