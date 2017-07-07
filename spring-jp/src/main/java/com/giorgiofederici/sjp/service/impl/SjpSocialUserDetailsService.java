package com.giorgiofederici.sjp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;

@Service
public class SjpSocialUserDetailsService implements SocialUserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

		User user = this.userDao.getUserByUsername(userId);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + userId);
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Authority authority : user.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			authorities.add(grantedAuthority);
		}

		return new SocialUser(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);

	}

}
