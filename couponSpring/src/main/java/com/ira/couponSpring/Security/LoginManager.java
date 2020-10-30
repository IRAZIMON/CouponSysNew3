package com.ira.couponSpring.Security;

import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.ira.couponSpring.Facade.AdminFacade;
import com.ira.couponSpring.Facade.CompanyFacade;
import com.ira.couponSpring.Facade.CustomerFacade;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Lazy
@Service
public class LoginManager {

	@Autowired
	private AdminFacade adminFacade;
	@Autowired
	private CompanyFacade companyFacade;
	@Autowired
	private CustomerFacade customerFacade;

	@Autowired
	private TokenManager tokenManager;

//	public int  getCompanyId()	{
//		return companyFacade.getCompanyId();
//	}

	public String login(String email, String password, Clientype clientype) throws LoginException {

		System.out.println("start loginmanager");

		switch (clientype) {

		case Administrator:

			if (adminFacade.login(email, password)) {

				return tokenManager.addToken(adminFacade);

			}else {
				throw new LoginException("Admin login is failed,sent from login manager");
			}

		case Company:

			if (companyFacade.login(email, password)) {

				return tokenManager.addToken(companyFacade);
			} else {
				throw new LoginException("Company login is failed,plaese try again");
			}

		case Customer:

			if (customerFacade.login(email, password)) {
				return tokenManager.addToken(customerFacade);
			} else {
				throw new LoginException("Customer login is failed,please try again");
			}

		default:
			System.out.println("end loginmanager");
			throw new LoginException("Unknown account. Please try again");

		}

	}

}
