package com.ira.couponSpring.Security;



import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ira.couponSpring.Facade.AdminFacade;
import com.ira.couponSpring.Facade.ClientFacade;
import com.ira.couponSpring.Facade.CompanyFacade;
import com.ira.couponSpring.Facade.CustomerFacade;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Lazy
//@Service
public class LoginManagerTest {

	@Autowired
	private AdminFacade adminFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private CustomerFacade customerFacade;

	public ClientFacade login(String email, String passward, Clientype clientype) throws SQLException, LoginException {

		switch (clientype) {

		case Administrator:

			if (adminFacade.login(email, passward)) {
			return adminFacade;
			} 
			throw new LoginException("Admin login is failed,please try again");
		
				
		case Company:

			if (companyFacade.login(email, passward)) {
			
				return companyFacade;	
			} 
			throw new LoginException("Company login is failed,plaese try again");

		case Customer:

			if (customerFacade.login(email, passward)) {
				return customerFacade;
			}
			throw new LoginException("Customer login is failed,please try again");
			

		default:
			
			throw new LoginException("Unknown account. Please try again");

			

		}

	}

}