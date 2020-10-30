package com.ira.couponSpring.clr;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Repo.CouponReposetory;
import com.ira.couponSpring.Utils.DateUtil;
import com.ira.couponSpring.Utils.PrintUtil;



@Order(4)
//@Component
public class DailyJobTest implements CommandLineRunner{

	@Autowired
	private CouponReposetory couponReposetory;
	
	
	@Override
	public void run(String... args) throws Exception {
		
	   Coupon testCoupon;
		System.out.println("Before remove");
 		PrintUtil.printCoupons(couponReposetory.findAll());
		testCoupon=couponReposetory.getOne(3);
	
		testCoupon.setEndDate(DateUtil.convertDate("2020-08-08"));
		couponReposetory.saveAndFlush(testCoupon);
	
	}

}
