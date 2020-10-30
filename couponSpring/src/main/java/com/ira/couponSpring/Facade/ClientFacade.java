package com.ira.couponSpring.Facade;

import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ira.couponSpring.Repo.CompanyReposetory;
import com.ira.couponSpring.Repo.CouponReposetory;
import com.ira.couponSpring.Repo.CustomerReposetory;



@Service
public abstract class ClientFacade {
	
	@Autowired
	protected CouponReposetory couponReposetory;
	
	@Autowired
	protected CompanyReposetory companyReposetory;
	
	@Autowired
	protected CustomerReposetory customerReposetory;
	
    
	public abstract boolean login(String email, String password)  throws LoginException;

}


