package com.finki.ukim.mk.library.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finki.ukim.mk.library.config.filters.JWTAuthenticationFilter;
import com.finki.ukim.mk.library.models.dto.UserDetailsDto;
import com.finki.ukim.mk.library.models.enums.Role;
import com.finki.ukim.mk.library.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDetailsDto> registerUser(@RequestParam String username, @RequestParam String password,@RequestParam String repeatPassword) throws Exception {
        this.userService.register(username,password,repeatPassword,"","", Role.LIBRARIAN);
        return ResponseEntity.ok(new UserDetailsDto());
    }

//    @PostMapping("/library/login")
//    public String doLogin(HttpServletRequest request,
//                          HttpServletResponse response) throws IOException {
//        Authentication auth = this.filter.attemptAuthentication(request, response);
//        return this.filter.generateJWT(response,auth);
//    }

}
