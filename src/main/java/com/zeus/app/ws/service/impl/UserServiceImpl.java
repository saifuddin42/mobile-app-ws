package com.zeus.app.ws.service.impl;

import com.zeus.app.ws.io.entity.UserEntity;
import com.zeus.app.ws.io.repository.UserRepository;
import com.zeus.app.ws.service.UserService;
import com.zeus.app.ws.shared.Utils;
import com.zeus.app.ws.shared.dao.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDTO createUser(UserDTO user) {

		UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());

		if(storedUserDetails != null) throw new RuntimeException("Record already exists");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserID(util.generateUserID(30));

		storedUserDetails = userRepository.save(userEntity);

		UserDTO returnVal = new UserDTO();
		BeanUtils.copyProperties(storedUserDetails, returnVal);
		return returnVal;
	}

	@Override
	public UserDTO getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if(userEntity == null) throw new UsernameNotFoundException(email);

		UserDTO returnVal = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnVal);
		return returnVal;
	}

	@Override
	public UserDTO getUserByUserID(String userID) {
		return null;
	}

	@Override
	public UserDTO updateUser(String userID, UserDTO user) {
		return null;
	}

	@Override
	public void deleteUser(String userID) {

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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // can use overridden this method to check for user in db for sign in
		UserEntity userEntity = userRepository.findByEmail(email); // we are using email as unique username

		if(userEntity == null) throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}
}
