package com.zeus.app.ws.service;

import com.zeus.app.ws.shared.dao.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO user);
	UserDTO getUser(String email);
	UserDTO getUserByUserID(String userID);
	UserDTO updateUser(String userID, UserDTO user);
	void deleteUser(String userID);
	List<UserDTO> getUsers(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);
}
