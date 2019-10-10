package com.idosinchuk.teagile.core.service.service;

import org.springframework.http.ResponseEntity;

import com.idosinchuk.teagile.core.service.dto.UserRequestDTO;

public interface UserService {

	ResponseEntity<?> getAllUsers();

	ResponseEntity<?> getUser(int userId);

	ResponseEntity<?> getUserByEmail(String email);

	ResponseEntity<?> getUserByEmailAndPassword(String email, String password);

	ResponseEntity<?> addUser(UserRequestDTO userRequestDTO);

	ResponseEntity<?> updateUser(int id, UserRequestDTO userRequestDTO);

	ResponseEntity<?> deleteUser(int userId);
}
