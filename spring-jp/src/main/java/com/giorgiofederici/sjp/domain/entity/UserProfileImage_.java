package com.giorgiofederici.sjp.domain.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserProfileImage.class)
public class UserProfileImage_ {
	public static volatile SingularAttribute<UserProfileImage, String> name;
	public static volatile SingularAttribute<UserProfileImage, String> type;
	public static volatile SingularAttribute<UserProfileImage, byte[]> content;
	public static volatile SingularAttribute<UserProfileImage, String> username;
}
