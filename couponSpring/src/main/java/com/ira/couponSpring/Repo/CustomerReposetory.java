package com.ira.couponSpring.Repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ira.couponSpring.Beans.Customer;

public interface CustomerReposetory extends JpaRepository<Customer, Integer> {

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

	Customer findById(int customer_id);

}
