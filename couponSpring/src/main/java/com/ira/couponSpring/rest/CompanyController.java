package com.ira.couponSpring.rest;

import java.util.List;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Beans.Credentials;
import com.ira.couponSpring.Beans.LoginResult;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.NotAllowedException;
import com.ira.couponSpring.Exceptions.NotExistsException;
import com.ira.couponSpring.Exceptions.NotFoundException;
import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.CompanyFacade;
import com.ira.couponSpring.Security.Clientype;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("company")
public class CompanyController extends ClientControler{

	@Autowired
	private LoginManager loginManager;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private CompanyFacade companyFacade;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Credentials credentials) throws Exception {
		
      LoginResult loginResult=new LoginResult();
		try {
			String token = loginManager.login(credentials.getEmail(), credentials.getPassword(), Clientype.Company);
			loginResult.setToken(token);
			loginResult.setType("company");
			System.out.println("end customer controller " + token);
			return new ResponseEntity<LoginResult>(loginResult,HttpStatus.OK);
		
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
		} catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException {
		return new ResponseEntity<List<Coupon>>(
				((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCoupons(), HttpStatus.OK);

	}

	@GetMapping("get-All-Coupons-ByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable(name = "category") CategoryOfCoupon category,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException {
		return new ResponseEntity<List<Coupon>>(
				((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCouponsByCategory(category),
				HttpStatus.OK);

	}

	@GetMapping("get-All-Coupons-ByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getAllCouponsByMaxPrice(@PathVariable(name = "maxPrice") int maxPrice,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException {
		return new ResponseEntity<List<Coupon>>(
				((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyCouponsByMaxPrice(maxPrice),
				HttpStatus.OK);

	}

	@GetMapping("get-company-details")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException,
			TokenNotExistException {
		return new ResponseEntity<Company>(((CompanyFacade) tokenManager.getClientFacade(token)).getCompanyDetails(),
				HttpStatus.OK);

	}

	
	

}
