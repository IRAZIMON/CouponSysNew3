package com.ira.couponSpring.Facade;

import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Exceptions.NotAllowedException;
import com.ira.couponSpring.Exceptions.NotFoundException;
import lombok.Data;

@Scope("prototype")
@Data
@Service
public class CompanyFacade extends ClientFacade {

	private int company_ID;

	public void setCompanyId(int company_ID) {
		this.company_ID = company_ID;
	}

	public CompanyFacade() {
		super();
	}

	public boolean login(String email, String password) throws LoginException {

		if (companyReposetory.findByEmailAndPassword(email, password) != null) {
			this.company_ID = (companyReposetory.findByEmailAndPassword(email, password).getId());
			return true;
		}

		return false;

	}

	public void addCoupon(Coupon coupon) throws Exception {
		Company comp = companyReposetory.getOne(company_ID);
		if (coupon != null) {
			for (Coupon c : comp.getCoupons()) {
				if (c.getTitle().equals(coupon.getTitle())) {
					throw new Exception("Coupon with such title already exists in this company");

				}
			}

		}
		couponReposetory.saveAndFlush(coupon);
		comp.getCoupons().add(coupon);
		companyReposetory.saveAndFlush(comp);

	}

	public void updateCoupon(Coupon coupon) throws NotAllowedException {
		for (Coupon coupon2 : getCompanyCoupons()) {
			if (coupon.getId() == coupon2.getId()) {
				if (coupon.getCompanyId() != coupon2.getCompanyId()) {
					throw new NotAllowedException("you can't update this coupon");
				}
			}
		}
		this.couponReposetory.save(coupon);

	}

	public void deleteCoupon(int coupon_id) {

		couponReposetory.deletePurchaseCoupon(coupon_id);
		couponReposetory.deleteById(coupon_id);

	}

	public List<Coupon> getCompanyCoupons() {

		return this.companyReposetory.getOne(this.company_ID).getCoupons();

	}

	public List<Coupon> getCompanyCouponsByCategory(CategoryOfCoupon category) throws NotFoundException {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> couponsCategory = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				couponsCategory.add(coupon);
			}

		}
		if (couponsCategory.isEmpty()) {
			throw new NotFoundException("No coupons with this category");
		}
		return couponsCategory;

	}

	public List<Coupon> getCompanyCouponsByMaxPrice(int maxPrice) throws NotFoundException {
		List<Coupon> couponsMaxPrice = new ArrayList<Coupon>();
		if (companyReposetory.existsById(this.company_ID)) {
			List<Coupon> coupons = getCompanyCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= maxPrice) {
					System.out.println("coupon " + coupon);
					couponsMaxPrice.add(coupon);
				}
			}

		} else {
			throw new NotFoundException("No coupons with maximum price");

		}
		return couponsMaxPrice;
	}

	public Company getCompanyDetails() {
		Company tmpCompany = companyReposetory.getOne(this.company_ID);
		tmpCompany.setCoupons(couponReposetory.findByCompanyId(company_ID));
		return tmpCompany;

	}

}