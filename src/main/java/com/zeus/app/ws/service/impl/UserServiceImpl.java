package com.zeus.app.ws.service.impl;

import com.zeus.app.ws.service.UserService;
import com.zeus.app.ws.shared.dao.UserDTO;

import java.util.List;

public class UserServiceImpl implements UserService {
	@Override
	public UserDTO createUser(UserDTO user) {
		return null;
	}

	@Override
	public UserDTO getUser(String email) {
		return null;
	}

	@Override
	public UserDTO getUserByUserId(String userId) {
		return null;
	}

	@Override
	public UserDTO updateUser(String userId, UserDTO user) {
		return null;
	}

	@Override
	public void deleteUser(String userId) {

	}

	@Override
	public List<UserDTO> getUsers(int page, int limit) {
		return null;
	}

	@Override
	public boolean verifyEmailToken(String token) {
		return false;
	}

	@Override
	public boolean requestPasswordReset(String email) {
		return false;
	}

	@Override
	public boolean resetPassword(String token, String password) {
		return false;
	}
}
