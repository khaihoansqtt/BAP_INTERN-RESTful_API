package com.bap.intern.RESTful_API.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bap.intern.RESTful_API.dto.getUsers.UserRes;
import com.bap.intern.RESTful_API.dto.postAndPatchUser.PatchUserReq;
import com.bap.intern.RESTful_API.dto.postAndPatchUser.PostUserReq;
import com.bap.intern.RESTful_API.dto.postAndPatchUser.PostAndPatchUserRes;
import com.bap.intern.RESTful_API.entity.User;
import com.bap.intern.RESTful_API.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public Page<UserRes> getUsersV1(int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		Page<User> users = userRepository.findAll(pageable);
		Page<UserRes> getUsersRes = users.map(user -> new UserRes(user));
		
		return getUsersRes;
	}
	
	public Page<UserRes> getUsersV2(String keySearch, int page, int limit, String[] sortParams) {
        
		Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        Pageable pageable = PageRequest.of(page, limit, sort);
        
		Page<User> users = keySearch == null ? userRepository.findAll(pageable)
											: userRepository.findByKey(keySearch, pageable);
		Page<UserRes> getUsersRes = users.map(user -> new UserRes(user));
		return getUsersRes;
	}
	
	public Page<UserRes> getUsersV3(String keySearch, int page, int limit, List<String> sortParams) {
		Sort sort = Sort.by(sortParams.stream().map(this::parseSortParam).collect(Collectors.toList()));

        Pageable pageable = PageRequest.of(page, limit, sort);
        
		Page<User> users = keySearch == null ? userRepository.findAll(pageable)
											: userRepository.findByKey(keySearch, pageable);
		Page<UserRes> getUsersRes = users.map(user -> new UserRes(user));
		return getUsersRes;
	}

    private Sort.Order parseSortParam(String sortParam) {
        String[] parts = sortParam.split(",");
        String field = parts[0];
        String direction = parts[1];
        System.out.println("field " + field);
        System.out.println("direction " + direction);
        return new Sort.Order(Sort.Direction.fromString(direction), field);
    }
    
	public UserRes getUserById(int userId) {
		User user = userRepository.findById(userId).get();
		return new UserRes(user);
	}
	public PostAndPatchUserRes postUser(PostUserReq req) {
		User user = User.builder().email(req.getEmail()).password(req.getPassword()).name(req.getName())
					.address(req.getAddress()).phoneNumber(req.getPhoneNumber()).description(req.getDescription())
					.status(0).deleteFlag(0).createdAt(new Date()).updatedAt(new Date()).build();
		userRepository.save(user);
		return new PostAndPatchUserRes(user, HttpStatus.CREATED.value());
	}
	
	public PostAndPatchUserRes patchUser(PatchUserReq req) {
		User user = userRepository.findById(req.getUserId()).get();
		if (req.getEmail() != null) user.setEmail(req.getEmail());
		if (req.getName() != null) user.setName(req.getName());
		if (req.getAddress() != null) user.setAddress(req.getAddress());
		if (req.getPhoneNumber() != null) user.setPhoneNumber(req.getPhoneNumber());
		if (req.getDescription() != null) user.setDescription(req.getDescription());
		
		userRepository.save(user);
		return new PostAndPatchUserRes(user, HttpStatus.OK.value());
	}
	
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}
}
