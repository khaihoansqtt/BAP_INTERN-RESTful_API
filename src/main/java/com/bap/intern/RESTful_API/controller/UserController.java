package com.bap.intern.RESTful_API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bap.intern.RESTful_API.dto.deleteUser.DeleteUserRes;
import com.bap.intern.RESTful_API.dto.postAndPatchUser.PatchUserReq;
import com.bap.intern.RESTful_API.dto.postAndPatchUser.PostUserReq;
import com.bap.intern.RESTful_API.repository.UserRepository;
import com.bap.intern.RESTful_API.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/api/v1/users")
	public ResponseEntity<?> getUsersV1(@RequestParam(value = "page", defaultValue = "0") int page,
									@RequestParam(value = "limit", defaultValue = "5") int limit) {
		
		return ResponseEntity.ok(userService.getUsersV1(page, limit));
	}

	@GetMapping("/api/v2/users")
	public ResponseEntity<?> getUsersV2(@RequestParam(value = "q", required = false) String searchKey,
								@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "limit", defaultValue = "5") int limit,
								@RequestParam(value = "sort", defaultValue = "user_id, asc") String[] sortParams) {
		System.out.println("q " + searchKey);
		System.out.println("page " + page);
		System.out.println("limit " + limit);
		System.out.println("sort " + sortParams[0]);
		System.out.println("sort " + sortParams[1]);
		
		return ResponseEntity.ok(userService.getUsersV2(searchKey, page, limit, sortParams));
	}

	@GetMapping("/api/v3/users")
	public ResponseEntity<?> getUsersV3(@RequestParam(value = "q", required = false) String searchKey,
								@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "limit", defaultValue = "5") int limit,
								@RequestParam(value = "sort", defaultValue = "user_id, asc") List<String> sortParams) {
		System.out.println("q " + searchKey);
		System.out.println("page " + page);
		System.out.println("limit " + limit);
		
		return ResponseEntity.ok(userService.getUsersV3(searchKey, page, limit, sortParams));
	}
	
	@GetMapping("/api/v1/users/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PostMapping("/api/v1/users")
	public ResponseEntity<?> postUser(@RequestBody PostUserReq req) {
		return new ResponseEntity(userService.postUser(req), HttpStatus.CREATED);
	}
	
	@PatchMapping("/api/v1/users")
	public ResponseEntity<?> patchUser(@RequestBody PatchUserReq req) {
		return new ResponseEntity(userService.patchUser(req), HttpStatus.OK);
	}
	
	@DeleteMapping("api/v1/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
