package com.ira.couponSpring.clr;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Customer;
import com.ira.couponSpring.Facade.AdminFacade;
import com.ira.couponSpring.Utils.PrintUtil;

@Component
public class ControlerTest implements CommandLineRunner{
	
	@Autowired
	private AdminFacade adminFacade;
	
	
	

	@Override
	public void run(String... args) throws Exception {
		
		PrintUtil.printTestInfo("Add new company");
		System.out.println();
		
		Company c1 = new Company();
		c1.setName("AAAA");
		c1.setEmail("AAAA@gmail.com");
		c1.setPassword("1111");
		adminFacade.addCompany(c1);
		
		
		Company c2 = new Company();
		c2.setName("BBBB");
		c2.setEmail("BBBB@gmail.com");
		c2.setPassword("Ip200683");
		adminFacade.addCompany(c2);
		
		
		Company c3 = new Company();
		c3.setName("WWW");
		c3.setEmail("WWW@gmail.com");
		c3.setPassword("6666");
		adminFacade.addCompany(c3);
		PrintUtil.printCompanies(adminFacade.getAllCompanies());
		
		System.out.println();
		PrintUtil.printTestInfo("Get All Companies");
		PrintUtil.printCompanies(adminFacade.getAllCompanies());
		System.out.println();
		
		 PrintUtil.printTestInfo("Add  new customer");
			Customer cust5 = new Customer();
			cust5.setFirstName("CCCC");
			cust5.setLastName("DDDD");
			cust5.setEmail("CCCC@walla.com");
			cust5.setPassword("3333");

			adminFacade.addCustumer(cust5);
			PrintUtil.printCustomer(cust5);
			System.out.println();
			
			
			Customer cus4 = new Customer();
			cus4.setFirstName("EEE");
			cus4.setLastName("FFF");
			cus4.setEmail("EEE@walla.com");
			cus4.setPassword("Ip200683");
			
			adminFacade.addCustumer(cus4);
			PrintUtil.printCustomer(cus4);
			System.out.println();
			
			Customer cus6 = new Customer();
			cus6.setFirstName("OOO");
			cus6.setLastName("PPP");
			cus6.setEmail("000@walla.com");
			cus6.setPassword("Az311218");
			
			adminFacade.addCustumer(cus6);
			PrintUtil.printCustomer(cus6);
			System.out.println();
			
			PrintUtil.printTestInfo("Get All Customers");
			PrintUtil.printCustomers(adminFacade.getAllCustomers());
			System.out.println();
		
	}
	
	
	
	
	
	

}
