package com.bap.intern.RESTful_API.dto.postAndPatchUser;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatchUserReq {
	private int userId;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phoneNumber;
	private String description;
}
