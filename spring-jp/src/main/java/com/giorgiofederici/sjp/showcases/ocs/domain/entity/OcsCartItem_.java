package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OcsCartItem.class)
public class OcsCartItem_ {
	public static volatile SingularAttribute<OcsCartItem, CartItemPrimaryKey> cartItemPrimaryKey;
	public static volatile SingularAttribute<OcsCartItem, OcsProduct> product;
	public static volatile SingularAttribute<OcsCartItem, OcsCart> cart;
	public static volatile SingularAttribute<OcsCartItem, Integer> quantity;
}
