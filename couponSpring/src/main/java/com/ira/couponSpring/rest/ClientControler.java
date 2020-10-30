package com.ira.couponSpring.rest;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;



public abstract class ClientControler {

	@Autowired
	LoginManager loginManager;

	@Autowired
	TokenManager tokenManager;

	public abstract ResponseEntity<?> login(@RequestParam String email, @RequestParam String password)throws LoginException;

}
