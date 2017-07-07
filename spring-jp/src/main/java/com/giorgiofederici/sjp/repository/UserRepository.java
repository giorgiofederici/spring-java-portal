package com.giorgiofederici.sjp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.giorgiofederici.sjp.domain.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Override
	public Page<User> findAll(Pageable pageable);

}
