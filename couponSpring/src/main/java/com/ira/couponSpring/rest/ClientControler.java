package com.ira.couponSpring.rest;

import javax.security.auth.login.LoginException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.ira.couponSpring.Beans.Credentials;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;



public abstract class ClientControler {

	@Autowired
	LoginManager loginManager;

	@Autowired
	TokenManager tokenManager;

	public abstract ResponseEntity<?> login(Credentials credentials)throws LoginException, Exception;

	

}
