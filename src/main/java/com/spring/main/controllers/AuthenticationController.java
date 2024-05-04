package com.spring.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.main.dtos.LoginResponse;
import com.spring.main.dtos.LoginUserDto;
import com.spring.main.dtos.RegisterUserDto;
import com.spring.main.entities.User;
import com.spring.main.services.AuthenticationService;
import com.spring.main.services.JwtService;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	private final JwtService jwtService;
	private final AuthenticationService authenticationService;

	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto ){
		User registeredUser=authenticationService.signup(registerUserDto);
		
		return ResponseEntity.ok(registeredUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
		User authenticatedUser=authenticationService.authenticate(loginUserDto);
	String jwtToken=jwtService.generateToken(authenticatedUser);
	
	LoginResponse loginResponse=new LoginResponse();
	loginResponse.setToken(jwtToken);
	loginResponse.setExpiresIn(jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);

	}

}
