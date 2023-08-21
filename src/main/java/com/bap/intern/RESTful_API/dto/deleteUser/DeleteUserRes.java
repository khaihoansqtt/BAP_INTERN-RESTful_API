package com.bap.intern.RESTful_API.dto.deleteUser;

import org.springframework.http.HttpStatus;

import com.bap.intern.RESTful_API.dto.getUsers.UserRes;
import com.bap.intern.RESTful_API.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteUserRes {
	private int status;
	private String message;
	private long timestamp;
	
	public DeleteUserRes() {
		status = HttpStatus.NO_CONTENT.value();
		message = "Delete user successfully";
		timestamp = System.currentTimeMillis();
	}
}
