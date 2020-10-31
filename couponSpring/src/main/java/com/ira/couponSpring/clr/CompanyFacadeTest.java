package com.ira.couponSpring.clr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Facade.CompanyFacade;
import com.ira.couponSpring.Repo.CompanyReposetory;
import com.ira.couponSpring.Repo.CouponReposetory;
import com.ira.couponSpring.Security.Clientype;

import com.ira.couponSpring.Security.LoginManagerTest;
import com.ira.couponSpring.Utils.DateUtil;
import com.ira.couponSpring.Utils.PrintUtil;

//@Order(2)
//@Component
public class CompanyFacadeTest implements CommandLineRunner {

	@Autowired
	private CompanyFacade companyFacade;

	@Autowired
	private CompanyReposetory companyReposetory;
	
	@Autowired
	private CouponReposetory couponReposetory;
	
    @Autowired
	private  LoginManagerTest loginManager;
	@Override
	public void run(String... args) throws Exception {

	
		PrintUtil.printTestInfo("Testing Company Facade");

		// login succssed
		System.out.println();
		PrintUtil.printTestInfo("Company login manager successed");
		try {
			companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		// login failed
		PrintUtil.printTestInfo("Company login manager failed ");
		try {
			companyFacade = (CompanyFacade) loginManager.login("Woshekem@gmail.com", "556600", Clientype.Company);
	     	System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
        System.out.println();
//===================================================================================================================

		
		System.out.println();
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		System.out.println();
        
		
		//add new coupons
        PrintUtil.printTestInfo("Add new coupon");
		Coupon co6 = new Coupon();
    	co6.setCompanyId(3);
		co6.setCategory(CategoryOfCoupon.FOOD);
		co6.setTitle("30%");
		co6.setDescription("buy one take one");
		co6.setStartDate(DateUtil.convertDate("2019-05-01"));
		co6.setEndDate(DateUtil.convertDate("2021-07-01"));
		co6.setAmount(900);
		co6.setPrice(70);
		co6.setImage("Dominos Pizza");
	
		try {
			companyFacade.addCoupon(co6);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		PrintUtil.printCoupon(co6);
		
		Coupon co7 = new Coupon();
		co7.setCompanyId(3);
		co7.setCategory(CategoryOfCoupon.ELECTRICITY);
		co7.setTitle("lottery");
		co7.setDescription("pepsi lottery");
		co7.setStartDate(DateUtil.convertDate("2019-04-01"));
		co7.setEndDate(DateUtil.convertDate("2021-06-01"));
		co7.setAmount(300);
		co7.setPrice(35);
		co7.setImage("pepsi buttle");
		
		try {
			companyFacade.addCoupon(co7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		PrintUtil.printCoupon(co7);
		System.out.println();
		
		PrintUtil.printTestInfo("Preview companies with coupons");
		PrintUtil.printCompanies(companyReposetory.findAll());
		System.out.println();
		
		//title already exist
	
		PrintUtil.printTestInfo("Add coupon with title already exists");
	    Coupon co8 = new Coupon();
		co8.setCompanyId(3);
		co8.setCategory(CategoryOfCoupon.FOOD);
		co8.setTitle("30%");
		co8.setDescription("buy one take one");
		co8.setStartDate(DateUtil.convertDate("2019-05-01"));
		co8.setEndDate(DateUtil.convertDate("2021-07-01"));
		co8.setAmount(900);
		co8.setPrice(70);
		co8.setImage("Dominos Pizza");
		
		try {

			companyFacade.addCoupon(co8);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		

		System.out.println();
		PrintUtil.printTestInfo("Before update coupon");
		PrintUtil.printCoupons(couponReposetory.findAll());

		System.out.println();
		PrintUtil.printTestInfo("Update coupon");
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		co6.setAmount(4500);
		try {
			companyFacade.updateCoupon(co6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
	}
		PrintUtil.printCoupons(couponReposetory.findAll());
		
		
		

		System.out.println();
		PrintUtil.printTestInfo("Delete coupon");
		companyFacade.deleteCoupon(2);
		PrintUtil.printCoupons(couponReposetory.findAll());
        System.out.println();
        
		PrintUtil.printTestInfo("Company coupons");
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		PrintUtil.printCoupons(companyFacade.getCompanyCoupons());
		System.out.println();
		
		
		
		PrintUtil.printTestInfo("Company Coupons By Category");
		
		// There are coupons with this category
		PrintUtil.printTestInfo("There are coupons with this category");
	    companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		try {
			PrintUtil.printCoupons(companyFacade.getCompanyCouponsByCategory(CategoryOfCoupon.FOOD));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// No coupons with this category
		PrintUtil.printTestInfo("No coupons with this category");
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		try {
			PrintUtil.printCoupons(companyFacade.getCompanyCouponsByCategory(CategoryOfCoupon.VACATION));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    
		System.out.println();
		PrintUtil.printTestInfo("Company coupons By Max Price");

		// There are coupons with max price
		PrintUtil.printTestInfo("There are coupons with max price");
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		try {
			PrintUtil.printCoupons(companyFacade.getCompanyCouponsByMaxPrice(300));	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// No coupons with maximum price
		PrintUtil.printTestInfo("No coupons with maximum price");
		companyFacade = (CompanyFacade) loginManager.login("shekem@gmail.com", "3021YY", Clientype.Company);
		try {
			PrintUtil.printCoupons(companyFacade.getCompanyCouponsByMaxPrice(30));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		PrintUtil.printTestInfo("Get Company Details");
		PrintUtil.printCompany(companyFacade.getCompanyDetails());
		
		System.out.println();
	
	}
}
