package com.ira.couponSpring.Facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Repo.CouponReposetory;

@Scope("prototype")
@Service
public class CouponFasede {
	
	@Autowired
	protected CouponReposetory couponReposetory;
	
	
	
	public List<Coupon>getAllCoupon(){
		
	return couponReposetory.findAll();
		
		
	}

}
