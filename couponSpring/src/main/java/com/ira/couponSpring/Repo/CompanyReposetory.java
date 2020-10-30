package com.ira.couponSpring.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ira.couponSpring.Beans.Company;

public interface CompanyReposetory extends JpaRepository<Company, Integer> {

	Company findByEmailAndPassword(String email, String password);

	Company findByEmail(String email);

	Company findByEmailAndName(String email, String name);

	Company findById(int id);

}
