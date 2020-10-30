package com.ira.couponSpring.Exceptions;

public class ExceptionLogin extends Exception {

	String email;

	public ExceptionLogin(String email) {

		super("email " + email + " Login is failed . Please try again");

		this.email = email;
	}

}
