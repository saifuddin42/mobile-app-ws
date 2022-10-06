package com.zeus.app.ws.service;

import com.zeus.app.ws.shared.dao.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
	UserDTO createUser(UserDTO user);
	UserDTO getUser(String email);
	UserDTO getUserByUserId(String userId);
	UserDTO updateUser(String userId, UserDTO user);
	void deleteUser(String userId);
	List<UserDTO> getUsers(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);
}
