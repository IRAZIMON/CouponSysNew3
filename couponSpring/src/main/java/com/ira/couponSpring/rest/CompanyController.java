package com.ira.couponSpring.rest;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.NotAllowedException;
import com.ira.couponSpring.Exceptions.NotExistsException;
import com.ira.couponSpring.Exceptions.NotFoundException;
import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.CompanyFacade;
import com.ira.couponSpring.Security.Clientype;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;
////@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private LoginManager loginManager;

	@Autowired
	private TokenManager tokenManager;

	

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) throws Exception {
		System.out.println("start company controller");
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			String token = loginManager.login(email, password, Clientype.Company);
			responseHeaders.set("Authorization", token);
			System.out.println("end company controller ");
			return ResponseEntity.ok().headers(responseHeaders).body("company login ssuccssed");

		} catch (LoginException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping("add-coupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Authorization", required = false) String token) throws Exception {
		try {
			tokenManager.isTokenExist(token);
		   ((CompanyFacade) tokenManager.getClientFacade(token)).addCoupon(coupon);
            return new ResponseEntity<Coupon>(HttpStatus.CREATED);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("update-coupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			 ((CompanyFacade) tokenManager.getClientFacade(token)).updateCoupon(coupon);
			return new ResponseEntity<Coupon>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("delete-coupon/{couponId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable(name = "couponId") int coupon_id,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			((CompanyFacade) tokenManager.getClientFacade(token)).deleteCoupon(coupon_id);
		return new ResponseEntity<Coupon>(HttpStatus.OK);
		}
		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException {
		return new ResponseEntity<List<Coupon>>(((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCoupons(), HttpStatus.OK);
		
	}

	@GetMapping("get-All-Coupons-ByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable(name = "category") CategoryOfCoupon category,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException {
            return new ResponseEntity<List<Coupon>>(((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCouponsByCategory(category), HttpStatus.OK);

	}


	@GetMapping("get-All-Coupons-ByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getAllCouponsByMaxPrice(@PathVariable(name = "maxPrice") double maxPrice,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException {
		  return new ResponseEntity<List<Coupon>>(((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCouponsByMaxPrice(maxPrice), HttpStatus.OK);
	
	}

	@GetMapping("getCompanyDetails/{int company_ID}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable(name = "company_ID") int company_ID,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException, TokenNotExistException {
		return new ResponseEntity<Company>(((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyDetails(company_ID),HttpStatus.OK);
	}

}
