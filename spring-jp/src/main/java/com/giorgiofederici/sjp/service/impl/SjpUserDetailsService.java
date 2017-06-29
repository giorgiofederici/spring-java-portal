package com.giorgiofederici.sjp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giorgiofederici.sjp.dao.UserDao;
import com.giorgiofederici.sjp.domain.entity.Authority;
import com.giorgiofederici.sjp.domain.entity.User;

@Service
public class SjpUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userDao.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Authority authority : user.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			authorities.add(grantedAuthority);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);

	}

}
