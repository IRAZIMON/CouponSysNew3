//package com.ira.couponSpring.clr;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import com.ira.couponSpring.Beans.CategoryOfCoupon;
//import com.ira.couponSpring.Beans.Coupon;
//import com.ira.couponSpring.Facade.CustomerFacade;
//import com.ira.couponSpring.Repo.CouponReposetory;
//import com.ira.couponSpring.Repo.CustomerReposetory;
//import com.ira.couponSpring.Security.Clientype;
//import com.ira.couponSpring.Security.LoginManagerFasade;
//import com.ira.couponSpring.Utils.DateUtil;
//import com.ira.couponSpring.Utils.PrintUtil;
//
////@Order(3)
////@Component
//public class CustomerFacadTest implements CommandLineRunner {
//
//    @Autowired
//    private CustomerFacade customerFacade;
//	@Autowired
//	private CouponReposetory couponReposetory;
//	
//	@Autowired
//	private CustomerReposetory customerReposetory;
//	
//	@Autowired
//	private LoginManagerFasade loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		PrintUtil.printTestInfo("Testing Customer Facade");
//
//		System.out.println();
//		
//		System.out.println();
//		PrintUtil.printTestInfo("Customer login manager successed");
//
//		try {
//			customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//
//		System.out.println();
//		PrintUtil.printTestInfo("Customer login manager failed ");
//
//		try {
//			customerFacade = (CustomerFacade) loginManager.login("tal@walla.com", "2234", Clientype.Customer);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//	
//
////===============================================================================================================
//
//		System.out.println();
//		customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//		System.out.println();
//		
//		PrintUtil.printTestInfo("Coupon purchase");
//		Coupon cp = new Coupon();
//		cp.setCompanyId(1);
//		cp.setCategory(CategoryOfCoupon.FOOD);
//		cp.setTitle("10%");
//		cp.setDescription("10%");
//		cp.setStartDate(DateUtil.convertDate("2019-05-01"));
//		cp.setEndDate(DateUtil.convertDate("2021-07-01"));
//		cp.setAmount(3000);
//		cp.setPrice(55);
//		cp.setImage("Dominos Pizza");
//
//		customerFacade.addPurchaseCoupons(cp);
//
//		Coupon cp1 = new Coupon();
//		cp1.setCompanyId(1);
//		cp1.setCategory(CategoryOfCoupon.VACATION);
//		cp1.setTitle("10%");
//		cp1.setDescription("10%");
//		cp1.setStartDate(DateUtil.convertDate("2019-05-01"));
//		cp1.setEndDate(DateUtil.convertDate("2023-07-01"));
//		cp1.setAmount(1000);
//		cp1.setPrice(1550);
//		cp1.setImage("Tv");
//		customerFacade.addPurchaseCoupons(cp1);
//		
//		
//		PrintUtil.printTestInfo("Preview Customers with coupons");
//		PrintUtil.printCustomers(customerReposetory.findAll());
//		System.out.println();
//		
//		
//
//		System.out.println();
//		PrintUtil.printTestInfo("Testing conditions for purchase a coupon");
//		System.out.println();
//
//		// more than one coupon
//		PrintUtil.printTestInfo("More than one coupon");
//		customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//		try {
//			customerFacade.addPurchaseCoupons(cp1);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//
//		}
//        System.out.println();
//		// amount 0
//		PrintUtil.printTestInfo("Amount 0");
//		customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//		Coupon cp2 = new Coupon();
//		cp2.setCompanyId(4);
//		cp2.setCategory(CategoryOfCoupon.FOOD);
//		cp2.setTitle("10%");
//		cp2.setDescription("10% discount");
//		cp2.setStartDate(DateUtil.convertDate("2020-03-23"));
//		cp2.setEndDate(DateUtil.convertDate("2021-05-11"));
//		cp2.setAmount(0);
//		cp2.setPrice(49);
//		cp2.setImage("Dinner");
//
//		try {
//			customerFacade.addPurchaseCoupons(cp2);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
////
//		// exspired coupon
//		System.out.println();
//		PrintUtil.printTestInfo("Exspired coupon");
//		customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//
//		Coupon cp3 = new Coupon();
//		cp3.setCompanyId(4);
//		cp3.setCategory(CategoryOfCoupon.FOOD);
//		cp3.setTitle("10%");
//		cp3.setDescription("10% discount");
//		cp3.setStartDate(DateUtil.convertDate("2020-03-23"));
//		cp3.setEndDate(DateUtil.convertDate("2020-05-11"));
//		cp3.setAmount(444);
//		cp3.setPrice(49);
//		cp3.setImage("Dinner");
//
//		try {
//			customerFacade.addPurchaseCoupons(cp3);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println();
//		
//		PrintUtil.printTestInfo("All purchase coupons");
//		PrintUtil.printCoupons(customerFacade.getAllCouponsPurchaseCustomer());
//        System.out.println();
//        
//		PrintUtil.printTestInfo("All purchase coupons By Category");
//
//		// There are coupons with this category
//        PrintUtil.printTestInfo("There are coupons with this category");
//		try {
//			PrintUtil.printCoupons(customerFacade.getCustomerCoupons(CategoryOfCoupon.FOOD));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		// No coupons with this category
//		PrintUtil.printTestInfo("No coupons with this category");
//		try {
//			PrintUtil.printCoupons(customerFacade.getCustomerCoupons(CategoryOfCoupon.RESTAURANT));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println();
//		PrintUtil.printTestInfo("All purchase coupons By Max Price");
//
//		// There are coupons with a maximum price
//		PrintUtil.printTestInfo("There are coupons with a maximum price");
//		try {
//			PrintUtil.printCoupons(customerFacade.getCustomerCouponsMax(2000));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		// No coupons with maximum price
//		PrintUtil.printTestInfo("NO coupons with a maximum price");
//		customerFacade = (CustomerFacade) loginManager.login("shai@walla.com", "77777", Clientype.Customer);
//		try {
//			PrintUtil.printCoupons(customerFacade.getCustomerCouponsMax(32));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println();
//		PrintUtil.printTestInfo("Customer Ditails");
//		PrintUtil.printCustomer(customerFacade.getCustomerDetails(2));
//
//		System.out.println();
//
//	}
//
//}
