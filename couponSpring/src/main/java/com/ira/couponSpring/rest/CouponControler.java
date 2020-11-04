package com.ira.couponSpring.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Facade.CouponFasede;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("coupons")
public class CouponControler {
	

	@Autowired
	private CouponFasede couponFacade;
	
	
	@GetMapping("get-all")
	public  ResponseEntity<?>getAllCoupon(){
	return new 	ResponseEntity <List<Coupon>>(couponFacade.getAllCoupon(), HttpStatus.OK);
}
}