package com.ira.couponSpring.Beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Customer {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Customer(int id, String firstName, String lastName, String email, String password, List<Coupon> coupons) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}
	
	public void addCoupon (Coupon coupon) {
		this.coupons.add(coupon);
	}

}
