package com.ira.couponSpring.Repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ira.couponSpring.Beans.CategoryOfCoupon;
import com.ira.couponSpring.Beans.Coupon;



public interface CouponReposetory extends JpaRepository<Coupon, Integer> {

List<Coupon> findByCompanyId(int companyId);		



Coupon findByIdAndTitle(int id, String title);
	
boolean findById(int coupon_id);

List<Coupon> findByCategory(CategoryOfCoupon category);

Coupon findByCompanyIdAndTitle(int company_id, String title);


	
@Transactional		
@Modifying
@Query(value = "DELETE from customer_coupons WHERE customer_id= :customer_id AND :coupons_id=:coupons_id", nativeQuery = true)
void deletePurchaseCouponCustomer(int coupons_id, int customer_id);

@Transactional		
@Modifying
@Query(value = "DELETE from customer_coupons WHERE coupons_id= :coupons_id", nativeQuery = true)
void deletePurchaseCoupon(int coupons_id);


@Transactional
@Modifying
@Query(value = "INSERT INTO company_coupons (company_id,coupons_id) VALUES (:company_id,:coupons_id)", nativeQuery = true)
void addCouponVsCompany(int company_id, int coupons_id);


@Transactional
@Modifying
@Query(value = "INSERT INTO customer_coupons (customer_id,coupons_id) VALUES (:customer_id,:coupons_id)", nativeQuery = true)
void addCouponPurchase(int customer_id,int coupons_id);



@Transactional
@Modifying
@Query(value = "INSERT INTO company_coupons (coupons_id) VALUES (:coupons_id)", nativeQuery = true)
void addCouponVsCompany( int coupons_id);





//==========================================================================================================


	

	

	

	

}
