package com.finki.ukim.mk.library.config;

import com.finki.ukim.mk.library.config.filters.JWTAuthenticationFilter;
import com.finki.ukim.mk.library.config.filters.JWTAuthorizationFilter;
import com.finki.ukim.mk.library.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        var authenticationFilter = new JWTAuthenticationFilter(authenticationManager(), userService, passwordEncoder);

        http.cors().and().csrf().disable();
//
//        authenticationFilter.setFilterProcessesUrl("/library/login");
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests().antMatchers("/book","/register").permitAll();
//
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.addFilter(authenticationFilter);
//
//        http.addFilterBefore(new JWTAuthorizationFilter(authenticationManager()),JWTAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().permitAll();


    }

}
