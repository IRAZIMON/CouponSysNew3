package com.ira.couponSpring;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ira.couponSpring.Beans.Coupon;

import lombok.Data;

//@EnableAutoConfiguration(exclude = {Coupon.class})
@EnableScheduling
@SpringBootApplication
public class CouponSpringApplication {

	public static void main(String[] args) {
	
	SpringApplication.run(CouponSpringApplication.class, args);
		System.out.println("coupon system in action!!!!!!!");
	}

}
