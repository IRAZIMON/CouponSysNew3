package com.ira.couponSpring.rest;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Credentials;
import com.ira.couponSpring.Beans.Customer;
import com.ira.couponSpring.Beans.LoginResult;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.NotAllowedException;
import com.ira.couponSpring.Exceptions.NotExistsException;
import com.ira.couponSpring.Exceptions.TokenNotExistException;
import com.ira.couponSpring.Facade.AdminFacade;
import com.ira.couponSpring.Security.Clientype;
import com.ira.couponSpring.Security.LoginManager;
import com.ira.couponSpring.Security.TokenManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("admin")
public class AdminController extends ClientControler{

	@Autowired
	private LoginManager loginManager;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private AdminFacade adminFacade;

	
	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestBody Credentials credentials) throws Exception {
		
      LoginResult loginResult=new LoginResult();
		try {
			String token = loginManager.login(credentials.getEmail(), credentials.getPassword(), Clientype.Administrator);
			loginResult.setToken(token);
			loginResult.setType("admin");
			
			return new ResponseEntity<LoginResult>(loginResult,HttpStatus.OK);
		
		} catch (LoginException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping("add-company")
	public ResponseEntity<?> addCompany(@RequestBody Company company,
			@RequestHeader(name = "Authorization", required = false) String token) throws AlreadyExistException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.addCompany(company);
			return new ResponseEntity<Company>(HttpStatus.CREATED);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.updateCompany(company);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("delete-company/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "companyId") int company_id,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.deleteCompany(company_id);
			;
			return new ResponseEntity<Company>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-all-companies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException {
		try {
			tokenManager.isTokenExist(token);
			ArrayList<Company> companies = (ArrayList<Company>) adminFacade.getAllCompanies();
			return new ResponseEntity<ArrayList<Company>>(companies, HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}
	}

	@GetMapping("getOneCompany/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable(name = "companyId") int company_id,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.getOneCompany(company_id);
			return new ResponseEntity<Company>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Authorization", required = false) String token) throws AlreadyExistException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.addCustumer(customer);
			return new ResponseEntity<Customer>(HttpStatus.CREATED);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("update-customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.updateCustumer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("delete-customer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "customerId") int customer_id,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.deleteCustomer(customer_id);
			;
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("get-all-customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException, NotExistsException {
		try {
			tokenManager.isTokenExist(token);
			ArrayList<Customer> customers = (ArrayList<Customer>) adminFacade.getAllCustomers();
			return new ResponseEntity<ArrayList<Customer>>(customers, HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}
	}

	@GetMapping("getOneCustomer/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable(name = "customerId") int customer_id,
			@RequestHeader(name = "Authorization", required = false) String token)
			throws AlreadyExistException, NotAllowedException {
		try {
			tokenManager.isTokenExist(token);
			adminFacade.getOneCustomer(customer_id);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}

		catch (TokenNotExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}

	}

	
	

}
