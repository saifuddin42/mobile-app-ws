package com.zeus.app.ws.ui.controller;

import com.zeus.app.ws.service.UserService;
import com.zeus.app.ws.shared.dao.UserDTO;
import com.zeus.app.ws.ui.model.request.UserDetailsRequestModel;
import com.zeus.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path="/{ID}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
								//produces ensures that the response is only
								// in that particular type in order (if you don't want to use dependency like jackson)
								// OR if produces is not mentioned at all it returns
								// in whatever format is asked by the Accept header in
								// the request (but requires the jackson xml dependency)
	public UserRest getUser(@PathVariable String ID) {
		UserRest response = new UserRest();

		UserDTO userDTO = userService.getUserByUserID(ID);
		BeanUtils.copyProperties(userDTO, response);

		return response;
	}

	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
				 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
