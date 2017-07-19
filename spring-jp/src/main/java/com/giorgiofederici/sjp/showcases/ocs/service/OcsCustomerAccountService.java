package com.giorgiofederici.sjp.showcases.ocs.service;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;
import com.giorgiofederici.sjp.showcases.ocs.domain.form.CustomerAccountForm;

public interface OcsCustomerAccountService {

	public void createCustomerAccount(CustomerAccountForm customerAccountForm);

	public OcsCustomerAccount getCustomerAccountByUsername(String username);

	public List<OcsCustomerAccount> findAll();

	public List<OcsCustomerAccount> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalCustomerAccountsNumber();

	public void updateCustomerAccount(CustomerAccountForm customerAccountForm);

	public void deleteCustomerAccount(String username);

}
