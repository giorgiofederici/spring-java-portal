package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OcsCustomerAccount.class)
public class OcsCustomerAccount_ {
	public static volatile SingularAttribute<OcsCustomerAccount, String> username;
	public static volatile SingularAttribute<OcsCustomerAccount, String> firstName;
	public static volatile SingularAttribute<OcsCustomerAccount, String> lastName;
	public static volatile SingularAttribute<OcsCustomerAccount, String> midName;
	public static volatile SingularAttribute<OcsCustomerAccount, Date> birthDate;
	public static volatile SingularAttribute<OcsCustomerAccount, String> address;
	public static volatile SingularAttribute<OcsCustomerAccount, String> email;
	public static volatile SingularAttribute<OcsCustomerAccount, String> mobile;
}
