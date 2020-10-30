//package com.ira.couponSpring.clr;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.ira.couponSpring.Beans.Company;
//import com.ira.couponSpring.Beans.Customer;
//import com.ira.couponSpring.Facade.AdminFacade;
//import com.ira.couponSpring.Security.Clientype;
//import com.ira.couponSpring.Security.LoginManagerFasade;
//import com.ira.couponSpring.Utils.PrintUtil;

//@Order(1)
//@Component
//public class AdminFacadeTest implements CommandLineRunner {
//	
//	@Autowired
//	LoginManagerFasade loginManager;
//
//	@Autowired
//	private AdminFacade adminFacade;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		PrintUtil.printTestInfo("Testing Admin Facade");
//		
//		System.out.println();
//		//login succeed
//		PrintUtil.printTestInfo("Admin login manager successed");
//		 try {
//        	adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", Clientype.Administrator);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println();
//		// login failed
//		PrintUtil.printTestInfo("Admin login manager is failed");
//		try {
//		adminFacade = (AdminFacade) loginManager.login("admin@adminadmin.com", "admin", Clientype.Administrator);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	
//		System.out.println();
//		
////==================================================================================================================		
//		
//		System.out.println();
//		adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", Clientype.Administrator);
//		System.out.println();
//
//        
//		PrintUtil.printTestInfo("Add new company");
//		System.out.println();
//
//		// add new companies
//		
//		Company c5 = new Company();
//		c5.setName("Mega");
//		c5.setEmail("Mega@gmail.com");
//		c5.setPassword("4444");
//		adminFacade.addCompany(c5);
////		PrintUtil.printCompany(c5);
//
//	
//		Company c6 = new Company();
//		c6.setName("Shekem");
//		c6.setEmail("shekem@gmail.com");
//		c6.setPassword("3021YY");
//		adminFacade.addCompany(c6);
////		PrintUtil.printCompany(c6);
//
//		Company c7 = new Company();
//		c7.setName("Tempo");
//		c7.setEmail("tempo@gmail.com");
//		c7.setPassword("3333");
//		adminFacade.addCompany(c7);
////		PrintUtil.printCompany(c7);
//		System.out.println();
//
//		// company already exists
//		PrintUtil.printTestInfo("Company is already exist");
//		try {
//			adminFacade.addCompany(c6);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println();
//		PrintUtil.printTestInfo("Before update company");
//		PrintUtil.printCompanies(adminFacade.getAllCompanies());
//        System.out.println();
//        
//		PrintUtil.printTestInfo("Update Company");
//		c7.setEmail("BYBY@walla.com");
//		try {
//			adminFacade.updateCompany(c7);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		PrintUtil.printCompanies(adminFacade.getAllCompanies());
//		System.out.println();
//
//		PrintUtil.printTestInfo("Delete Company");
////		adminFacade.deleteCompany(1);
////		PrintUtil.printCompanies(adminFacade.getAllCompanies());
//		System.out.println();
//
//		PrintUtil.printTestInfo("Get One Company");
//		PrintUtil.printCompany(adminFacade.getOneCompany(2));
//		System.out.println();
//
//		PrintUtil.printTestInfo("Get All Companies");
//		PrintUtil.printCompanies(adminFacade.getAllCompanies());
//
//		System.out.println();
//		adminFacade = (AdminFacade) loginManager.login("admin@admin.com", "admin", Clientype.Administrator);
//		System.out.println();
//
//		
//		// add new customer
//	    PrintUtil.printTestInfo("Add  new customer");
//	    
//	    Customer cust4 = new Customer();
//		cust4.setFirstName("Avi");
//		cust4.setLastName("Shaily");
//		cust4.setEmail("shaily@walla.com");
//		cust4.setPassword("1111");
//
//		adminFacade.addCustumer(cust4);
////		PrintUtil.printCustomer(cust4);
//		System.out.println();
//	   
//		Customer cust5 = new Customer();
//		cust5.setFirstName("Danit");
//		cust5.setLastName("Shai");
//		cust5.setEmail("aaaa@walla.com");
//		cust5.setPassword("881199");
//
//		adminFacade.addCustumer(cust5);
////		PrintUtil.printCustomer(cust5);
//		System.out.println();
//		
//		Customer cust6 = new Customer();
//		cust6.setFirstName("Debi");
//		cust6.setLastName("Zohar");
//		cust6.setEmail("shai@walla.com");
//		cust6.setPassword("77777");
//		adminFacade.addCustumer(cust6);
////		PrintUtil.printCustomer(cust6);
//		System.out.println();
//
//		// customer already exists
//		PrintUtil.printTestInfo("Customer is already exists");
//		try {
//			adminFacade.addCustumer(cust6);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		System.out.println();
//		PrintUtil.printTestInfo("Before update customer");
//		PrintUtil.printCustomers(adminFacade.getAllCustomers());
//        System.out.println();
//
//		System.out.println();
//		PrintUtil.printTestInfo("Update customer");
//		cust5.setFirstName("Bob");
//		try {
//			adminFacade.updateCustumer(cust5);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		PrintUtil.printCustomers(adminFacade.getAllCustomers());
//		System.out.println();
//
//		PrintUtil.printTestInfo("Delete customer");
////		adminFacade.deleteCustomer(1);
////		PrintUtil.printCustomers(adminFacade.getAllCustomers());
//		System.out.println();
//
//		PrintUtil.printTestInfo("Get All Customers");
//		PrintUtil.printCustomers(adminFacade.getAllCustomers());
//		System.out.println();
//
//		PrintUtil.printTestInfo("Get One Customer");
//		PrintUtil.printCustomer(adminFacade.getOneCustomer(2));
//
//		System.out.println();
//
//	}
//
//}
