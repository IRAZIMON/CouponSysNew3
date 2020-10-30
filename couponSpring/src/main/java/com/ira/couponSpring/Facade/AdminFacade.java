package com.ira.couponSpring.Facade;



import java.util.List;
import javax.security.auth.login.LoginException;
import org.springframework.stereotype.Service;
import com.ira.couponSpring.Beans.Company;
import com.ira.couponSpring.Beans.Coupon;
import com.ira.couponSpring.Beans.Customer;
import com.ira.couponSpring.Exceptions.AlreadyExistException;
import com.ira.couponSpring.Exceptions.NotAllowedException;

@Service
public class AdminFacade extends ClientFacade {

	

	public AdminFacade() {
		super();
	}


	public boolean login(String email, String password) throws LoginException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;

	}

	public void addCompany(Company company) throws AlreadyExistException {
		boolean isExist = false;
		List<Company> companies = companyReposetory.findAll();
		for (Company company2 : companies) {
			if (company.getEmail().equals(company2.getEmail()) || company.getName().equals(company2.getName())) {
				isExist = true;
				break;
			}
		}
		if (isExist) {
			throw new AlreadyExistException("Company email or name is already exist,can't add new company");
		}

		companyReposetory.save(company);
	}

	public void updateCompany(Company company) throws NotAllowedException {
		boolean isExist = false;
		List<Company> companies = companyReposetory.findAll();
		for (Company company2 : companies) {
			if (company.getId() == company2.getId() && company.getName().equals(company2.getName())) {
				this.companyReposetory.saveAndFlush(company);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			throw new NotAllowedException("you can't update this company");

		}
	}

	public void deleteCompany(int company_id) {
		Company company = companyReposetory.getOne(company_id);
		Coupon coupon = null;
		company.deleteCompanyCoupon(coupon);
		companyReposetory.deleteById(company_id);
	}

	public List<Company> getAllCompanies() {
		List<Company> companies = companyReposetory.findAll();
		return companies;
	}

	public Company getOneCompany(int id) {

		Company tmpCompany =  companyReposetory.getOne(id);

		return tmpCompany;

	}

	public void addCustumer(Customer customer) throws AlreadyExistException {
		boolean isExists = false;

		List<Customer> customers = customerReposetory.findAll();
		for (Customer customer2 : customers) {
			if (customer.getEmail().equals(customer2.getEmail())) {
				isExists = true;
				break;

			}
		}
		if (isExists) {
			throw new AlreadyExistException("Customer email already exist,can't add new customer");
		}
		customerReposetory.save(customer);

	}

	public void updateCustumer(Customer customer) throws NotAllowedException {
		boolean isExists = false;
		List<Customer> customers = getAllCustomers();
		for (Customer customer2 : customers) {
			if (customer.getId() == customer2.getId()) {
				this.customerReposetory.saveAndFlush(customer);
				isExists = true;
				break;
			}
		}
		if (!isExists) {
			throw new NotAllowedException("you can't update this customer");
		}

	}

	public void deleteCustomer(int customer_id) {

		Customer customer = customerReposetory.getOne(customer_id);

		customerReposetory.deleteById(customer_id);

	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerReposetory.findAll();
		return customers;
	}

	public Customer getOneCustomer(int customer_id) {
		Customer tmpCustomer = customerReposetory.getOne(customer_id);
		return tmpCustomer;

	}

}
