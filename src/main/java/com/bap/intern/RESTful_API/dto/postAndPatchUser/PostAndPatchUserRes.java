package com.bap.intern.RESTful_API.dto.postAndPatchUser;

import org.springframework.http.HttpStatus;

import com.bap.intern.RESTful_API.dto.getUsers.UserRes;
import com.bap.intern.RESTful_API.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostAndPatchUserRes {
	private int status;
	private String message;
	private long timestamp;
	private UserRes user;
	
	public PostAndPatchUserRes(User user, int httpStatus) {
		status = httpStatus;
		message = "Create/update user successfully";
		timestamp = System.currentTimeMillis();
		this.user = new UserRes(user);
	}
}
