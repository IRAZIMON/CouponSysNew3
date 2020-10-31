package com.ira.couponSpring.Facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.security.auth.login.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Beans.Customer;
import com.ira.couponSpring.Exceptions.NotExistsException;
import com.ira.couponSpring.Exceptions.NotFoundException;
import com.ira.couponSpring.Exceptions.PurchaseCouponException;
import lombok.Data;

@Scope("prototype")

@Service
public class CustomerFacade extends ClientFacade {

	private int customer_ID;

	public int getCustomer_ID() {
		return customer_ID;
	}

	public CustomerFacade() {

		super();
	}

	public boolean login(String email, String password) throws LoginException {

		if (customerReposetory.findByEmailAndPassword(email, password) != null) {
			this.customer_ID = (customerReposetory.findByEmailAndPassword(email, password).getId());
			return true;
		}
		return false;

	}

	public void addPurchaseCoupons(Coupon coupon) throws ParseException, PurchaseCouponException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(LocalDate.now().minusDays(5).toString());

		Customer customer = customerReposetory.getOne(customer_ID);

		for (Coupon coup : customer.getCoupons()) {
			if (coup.getId() == coupon.getId()) {
				throw new PurchaseCouponException("you cannot purchase more than one coupon");

			}
		}

		if (coupon.getAmount() == 0) {
			throw new PurchaseCouponException("you cannot purchase coupon if amount is 0");
		}

		boolean isExpired = date1.after(coupon.getEndDate());
		if (isExpired) {
			throw new PurchaseCouponException("You cannot purchase expired coupon");
		}
		int amount = coupon.getAmount();
		coupon.setAmount(amount - 1);
		couponReposetory.saveAndFlush(coupon);
		customer.getCoupons().add(coupon);
		customerReposetory.saveAndFlush(customer);

	}

	public List<Coupon> getAllCouponsPurchaseCustomer() throws NotFoundException {

		Customer customer = customerReposetory.findById(customer_ID);
		if (customer != null) {
			List<Coupon> coupons = customer.getCoupons();
				return coupons;
			} else {
				throw new NotFoundException("This customer not exists");
			}
		

	}

	public List<Coupon> getCustomerCoupons(CategoryOfCoupon category) throws NotFoundException {
		List<Coupon> coupons = getAllCouponsPurchaseCustomer();
		List<Coupon> couponNewList = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				couponNewList.add(coupon);

			}
		}
		if (couponNewList.isEmpty()) {

			throw new NotFoundException("No coupons of this category");
		}
		return couponNewList;
	}

	public List<Coupon> getCustomerCouponsMax(double maxPrice) throws NotFoundException {

		List<Coupon> coupons = getAllCouponsPurchaseCustomer();
		List<Coupon> couponNewList = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				couponNewList.add(coupon);

			} else {
				throw new NotFoundException("No coupons with maximum price");
			}

		}
		return couponNewList;

	}

	public Customer getCustomerDetails() {
		Customer tmpCustomer = customerReposetory.getOne(this.customer_ID);
		tmpCustomer.setCoupons(couponReposetory.findAll());
		return tmpCustomer;
	}

}