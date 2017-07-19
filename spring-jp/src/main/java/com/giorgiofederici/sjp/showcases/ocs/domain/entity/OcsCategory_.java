package com.giorgiofederici.sjp.showcases.ocs.domain.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OcsCategory.class)
public class OcsCategory_ {
	public static volatile SingularAttribute<OcsCategory, Integer> id;
	public static volatile SingularAttribute<OcsCategory, String> name;
}
