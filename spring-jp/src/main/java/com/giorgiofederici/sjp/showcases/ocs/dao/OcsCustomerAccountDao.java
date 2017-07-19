package com.giorgiofederici.sjp.showcases.ocs.dao;

import java.util.List;

import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsCustomerAccount;

public interface OcsCustomerAccountDao {

	public void createCustomerAccount(OcsCustomerAccount customerAccount);

	public OcsCustomerAccount getCustomerAccountByUsername(String username);

	public List<OcsCustomerAccount> findAll();

	public List<OcsCustomerAccount> findAll(int start, int length, int orderColumn, String orderDir, String searchValue);

	public int getTotalCustomerAccountsNumber();

	public void updateCustomerAccount(OcsCustomerAccount customerAccount);

	public void deleteCustomerAccount(String username);

}
