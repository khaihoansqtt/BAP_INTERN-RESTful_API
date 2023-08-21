package com.bap.intern.RESTful_API.dto.getUsers;

import com.bap.intern.RESTful_API.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRes extends User {
	public UserRes(User user) {
		setUserId(user.getUserId());
		setName(user.getName());
		setEmail(user.getEmail());
		setAddress(user.getAddress());
		setPhoneNumber(user.getPhoneNumber());
		setDescription(user.getDescription());
		setStatus(user.getStatus());
		setDescription(user.getDescription());
		setCreatedAt(user.getCreatedAt());
		setUpdatedAt(user.getUpdatedAt());
	}
}
