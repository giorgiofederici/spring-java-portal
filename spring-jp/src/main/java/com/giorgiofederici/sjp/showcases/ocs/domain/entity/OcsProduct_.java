package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OcsCustomerAccount.class)
public class OcsProduct_ {
	public static volatile SingularAttribute<OcsProduct, Integer> id;
	public static volatile SingularAttribute<OcsProduct, Integer> category;
	public static volatile SingularAttribute<OcsProduct, String> name;
	public static volatile SingularAttribute<OcsProduct, Double> price;
	public static volatile SingularAttribute<OcsProduct, Integer> stock;
}
