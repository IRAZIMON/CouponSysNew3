package com.ira.couponSpring.rest;

import java.text.ParseException;
import java.util.List;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Beans.Credentials;
import com.ira.couponSpring.Beans.LoginResult;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.NotAllowedException;
import com.ira.couponSpring.Exceptions.NotExistsException;
import com.ira.couponSpring.Exceptions.NotFoundException;
import com.ira.couponSpring.Exceptions.PurchaseCouponException;
import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.CustomerFacade;
import com.ira.couponSpring.Security.Clientype;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("customer")
public class CustomerController extends ClientControler{

	@Autowired
	private LoginManager loginManager;

	@Autowired
	private TokenManager tokenManager;

	

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Credentials credentials) throws Exception {
		System.out.println("start customer controller");
      LoginResult loginResult=new LoginResult();
		try {
			String token = loginManager.login(credentials.getEmail(), credentials.getPassword(), Clientype.Customer);
			loginResult.setToken(token);
			loginResult.setType("customer");
			System.out.println("end customer controller " + token);
			return new ResponseEntity<LoginResult>(loginResult,HttpStatus.OK);
		
		} catch (LoginException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping("add-purchaseCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, ParseException, PurchaseCouponException {
		try {
			tokenManager.isTokenExist(token);
			((CustomerFacade) tokenManager.getClientFacade(token)).addPurchaseCoupons(coupon);
			return new ResponseEntity<Coupon>(HttpStatus.CREATED);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-all-purchase-coupon")
	public ResponseEntity<?> getAllCouponsPurchaseCustomer(
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, ParseException, PurchaseCouponException, NotFoundException {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacade) tokenManager.getClientFacade(token)).getAllCouponsPurchaseCustomer(), HttpStatus.OK);
		}
		catch (TokenNotExistException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-all-purchase-coupons-by-category/{category}")
	public ResponseEntity<?> getAllCouponsPurchaseCustomerByCategory(
			@PathVariable(name = "category") CategoryOfCoupon category,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, ParseException, PurchaseCouponException, NotFoundException {
		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
					((CustomerFacade) tokenManager.getClientFacade(token)).getCustomerCoupons(category),HttpStatus.OK);
		}

		catch (TokenNotExistException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-All-purchase-coupons-ByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getAllCouponsByMaxPrice(@PathVariable(name = "maxPrice") int maxPrice,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException {

		try {
			tokenManager.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(
			((CustomerFacade) tokenManager.getClientFacade(token)).getCustomerCouponsMax(maxPrice),HttpStatus.OK);

		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping("get-customer-details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException, NotFoundException,
			TokenNotExistException {
		try {
			tokenManager.isTokenExist(token);
			((CustomerFacade) tokenManager.getClientFacade(token)).getCustomerDetails();
			return new ResponseEntity<Coupon>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}
}