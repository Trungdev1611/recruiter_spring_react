package com.recruiter.recruiter.controller;

import com.recruiter.recruiter.dto.request.RequestLogin;
import com.recruiter.recruiter.dto.request.UserRequest;
import com.recruiter.recruiter.dto.response.UserResponse;
import com.recruiter.recruiter.models.User;
import com.recruiter.recruiter.services.AuthService;
import com.recruiter.recruiter.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {
    @Autowired
    private  AuthService authService;

    @PostMapping("/auth/signup")
    ResponseEntity<Response> signUp(@Valid @RequestBody UserRequest userRequest) {
        Response response = Response.successResponse(authService.signUp(userRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/auth/active-account/{token}")
    ResponseEntity<Response> activeAccount(@PathVariable String token) {
        Response response = Response.successResponse(authService.verifyRegisterToken(token));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    ResponseEntity<Response> login(@Valid @RequestBody RequestLogin userLogin) {
        Response response = Response.successResponse(authService.login(userLogin));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
