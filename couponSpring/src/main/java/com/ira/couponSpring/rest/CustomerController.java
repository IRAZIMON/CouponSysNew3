package com.ira.couponSpring.rest;

import java.text.ParseException;


import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.PurchaseCouponException;
import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.CustomerFacade;
import com.ira.couponSpring.Security.Clientype;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;

//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
//@RestController
//@RequestMapping("customer")
//public class CustomerController {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Autowired
//	private TokenManager tokenManager;
//
//	@Autowired
//	private CustomerFacade customerFacade;
//
//	@PostMapping("login")
//	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) throws Exception {
//		System.out.println("start customer controller");
//		HttpHeaders responseHeaders = new HttpHeaders();
//		try {
//			String token = loginManager.login(email, password, Clientype.Customer);
//			responseHeaders.set("project-header", token);
//			System.out.println("end customer controller ");
//			return ResponseEntity.ok().headers(responseHeaders).body("customer login ssuccssed");
//
//		} catch (LoginException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//
//		}
//
//	}
//
//	@PostMapping("add-purchaseCoupon")
//	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
//			@RequestHeader(name = "Authorization", required = false) String token)
//			throws AlreadyExistException, ParseException, PurchaseCouponException {
//		try {
//			tokenManager.isTokenExist(token);
//			
//			((CustomerFacade) tokenManager.getClientFacade(token)).addPurchaseCoupons(coupon);
////			customerFacade.addPurchaseCoupons(coupon);
//			return new ResponseEntity<Coupon>(HttpStatus.CREATED);
//		}
//
//		catch (TokenNotExistException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//		}
//	}
//}