package com.zeus.app.ws.ui.controller;

import com.zeus.app.ws.service.UserService;
import com.zeus.app.ws.shared.dao.UserDTO;
import com.zeus.app.ws.ui.model.request.UserDetailsRequestModel;
import com.zeus.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "getUser was called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

		UserRest response = new UserRest();
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO createdUser = userService.createUser(userDTO);
		BeanUtils.copyProperties(createdUser, response);

		return response;
	}

	@PutMapping
	public String updateUser() {
		return "updateUser was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "deleteUser was called";
	}
}
