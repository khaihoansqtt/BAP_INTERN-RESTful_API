package com.bap.intern.RESTful_API.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bap.intern.RESTful_API.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select * from users where name like %:keySearch%"
				+ "	or email like %:keySearch% or address like %:keySearch%"
				+ " or phone_number like %:keySearch%", nativeQuery = true)
	Page<User> findByKey(@Param("keySearch") String keySearch, Pageable pageable);

	@Override
	@Query(value = "select * from users", nativeQuery = true)
	Page<User> findAll(Pageable pageable);
}
