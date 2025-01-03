package com.velazquez.apirestpi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.velazquez.apirestpi.services.impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class MainSecurityConfiguration {

    @Autowired
    UsuarioServiceImpl jpaUser;

    @Bean
    public void AuthenticationManagerBuilder(HttpSecurity httpSec) throws Exception {
        AuthenticationManagerBuilder AuthenticationManagerBuilder = httpSec.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManagerBuilder.
            userDetailsService(jpaUser).
            passwordEncoder(getPasswordEncoder());
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(15);
    }
}
