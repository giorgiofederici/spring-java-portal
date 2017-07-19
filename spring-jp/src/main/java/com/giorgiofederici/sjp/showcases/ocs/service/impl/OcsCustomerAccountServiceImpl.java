package com.giorgiofederici.sjp.showcases.ocs.service.impl;

import java.util.Date;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.User;
import com.giorgiofederici.sjp.showcases.ocs.dao.OcsCustomerAccountDao;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CustomerAccountForm;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsCustomerAccountService;

@Service
@Transactional
public class OcsCustomerAccountServiceImpl implements OcsCustomerAccountService {

	@Autowired
	private OcsCustomerAccountDao customerAccountDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProcessEngine processEngine;

	@Override
	public void createCustomerAccount(CustomerAccountForm customerAccountForm) {

		String username = customerAccountForm.getUsername();

		OcsCustomerAccount customerAccount = new OcsCustomerAccount();

		customerAccount.setUsername(customerAccountForm.getUsername());
		customerAccount.setFirstName(customerAccountForm.getFirstName());
		customerAccount.setLastName(customerAccountForm.getLastName());
		customerAccount.setMidName(customerAccountForm.getMidName());
		customerAccount.setEmail(customerAccountForm.getEmail());
		customerAccount.setMobile(customerAccountForm.getMobile());
		customerAccount.setBirthDate(customerAccountForm.getBirthDate());
		customerAccount.setAddress(customerAccountForm.getAddress());
		customerAccount.setStartDate(new Date());

		User user = this.userDao.getUserByUsername(username);
		customerAccount.setUser(user);

		this.customerAccountDao.createCustomerAccount(customerAccount);

		// Activity User Creation
		IdentityService identityService = processEngine.getIdentityService();
		org.activiti.engine.identity.User activityUser = identityService.newUser(username);
		activityUser.setPassword(user.getPassword());
		identityService.saveUser(activityUser);

		try {
			identityService.saveGroup(identityService.newGroup("payor"));
			identityService.saveGroup(identityService.newGroup("shipper"));
			identityService.saveGroup(identityService.newGroup("shopper"));
			identityService.saveGroup(identityService.newGroup("buyer"));
		} catch (Exception e) {

		}

		identityService.createMembership(username, "shopper");
		identityService.createMembership(username, "buyer");
		identityService.createMembership(username, "payor");
		identityService.createMembership(username, "shipper");

	}

	@Override
	public OcsCustomerAccount getCustomerAccountByUsername(String username) {
		return this.customerAccountDao.getCustomerAccountByUsername(username);
	}

	@Override
	public List<OcsCustomerAccount> findAll() {
		return this.customerAccountDao.findAll();
	}

	@Override
	public List<OcsCustomerAccount> findAll(int start, int length, int orderColumn, String orderDir, String searchValue) {
		return this.customerAccountDao.findAll(start, length, orderColumn, orderDir, searchValue);
	}

	@Override
	public int getTotalCustomerAccountsNumber() {
		return this.customerAccountDao.getTotalCustomerAccountsNumber();
	}

	@Override
	public void updateCustomerAccount(CustomerAccountForm customerAccountForm) {
		OcsCustomerAccount customerAccount = new OcsCustomerAccount();
		User user = this.userDao.getUserByUsername(customerAccountForm.getUsername());
		customerAccount.setUser(user);
		customerAccount.setUsername(customerAccountForm.getUsername());
		customerAccount.setFirstName(customerAccountForm.getFirstName());
		customerAccount.setLastName(customerAccountForm.getLastName());
		customerAccount.setMidName(customerAccountForm.getMidName());
		customerAccount.setEmail(customerAccountForm.getEmail());
		customerAccount.setMobile(customerAccountForm.getMobile());
		customerAccount.setBirthDate(customerAccount.getBirthDate());

		this.customerAccountDao.updateCustomerAccount(customerAccount);
	}

	@Override
	public void deleteCustomerAccount(String username) {
		this.customerAccountDao.deleteCustomerAccount(username);

	}

}
