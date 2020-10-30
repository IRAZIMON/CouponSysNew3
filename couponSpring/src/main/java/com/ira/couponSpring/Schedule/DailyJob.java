package com.ira.couponSpring.Schedule;

import java.text.SimpleDateFormat;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Repo.CouponReposetory;
import com.ira.couponSpring.Utils.PrintUtil;
import com.sun.el.parser.ParseException;
import java.util.Date;
import java.util.List;




//@Component
public class DailyJob {

	@Autowired
	private CouponReposetory couponReposetory;
	
     //remove every 24 hours
	// @Scheduled(fixedRate=1000*60*60*24)
	
    @Scheduled(fixedRate=1000)
	public void deleteExpiredCoupons() throws ParseException, java.text.ParseException {
//    	System.out.println("before remove");
// 		PrintUtil.printCoupons(couponDBDAO.getAllcoupons());
		List<Coupon> coupons =couponReposetory.findAll();
		for (Coupon couponID : coupons) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(LocalDate.now().toString());
			boolean isExpired = date1.after(couponID.getEndDate());
			
			if (isExpired) {
				couponReposetory.deletePurchaseCoupon(couponID.getId());
				couponReposetory.deleteById(couponID.getId());   
			}
			
		}
	   System.out.println("After");
       PrintUtil.printCoupons(couponReposetory.findAll());
	}

}

