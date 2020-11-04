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
		c1.setName("Aroma");
		c1.setEmail("Aroma@gmail.com");
		c1.setPassword("Aa111111");
		adminFacade.addCompany(c1);

		
		
		 
		Company c2 = new Company();
		c2.setName("Arkia");
		c2.setEmail("Arkia@gmail.com");
		c2.setPassword("Ip200683");
		adminFacade.addCompany(c2);
		 
		
		Company c3 = new Company();
		c3.setName("DominosPizza");
		c3.setEmail("Pizza@gmail.com");
		c3.setPassword("Aa222222");
		adminFacade.addCompany(c3);
		PrintUtil.printCompanies(adminFacade.getAllCompanies());
		
		System.out.println();
		PrintUtil.printTestInfo("Get All Companies");
		PrintUtil.printCompanies(adminFacade.getAllCompanies());
		System.out.println();
		
		 PrintUtil.printTestInfo("Add  new customer");
			Customer cust5 = new Customer();
			cust5.setFirstName("Avi");
			cust5.setLastName("Cohen");
			cust5.setEmail("Avi@walla.com");
			cust5.setPassword("Aa333333");

			adminFacade.addCustumer(cust5);
			PrintUtil.printCustomer(cust5);
			System.out.println();
			
			
			Customer cus4 = new Customer();
			cus4.setFirstName("Dana");
			cus4.setLastName("Ron");
			cus4.setEmail("Dana@walla.com");
			cus4.setPassword("Ip200683");
			
			adminFacade.addCustumer(cus4);
			PrintUtil.printCustomer(cus4);
			System.out.println();
			
			Customer cus6 = new Customer();
			cus6.setFirstName("Or");
			cus6.setLastName("Levi");
			cus6.setEmail("Or@walla.com");
			cus6.setPassword("Iz200683");
			
			adminFacade.addCustumer(cus6);
			PrintUtil.printCustomer(cus6);
			System.out.println();
			
			PrintUtil.printTestInfo("Get All Customers");
			PrintUtil.printCustomers(adminFacade.getAllCustomers());
			System.out.println();
		
	}
	
	
	
	
	
	

}
