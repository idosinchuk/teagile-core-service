package com.idosinchuk.teagile.core.service.service.impl;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idosinchuk.teagile.core.service.common.ArrayListCustomMessage;
import com.idosinchuk.teagile.core.service.common.CustomErrorType;
import com.idosinchuk.teagile.core.service.common.CustomMessage;
import com.idosinchuk.teagile.core.service.entity.UserEntity;
import com.idosinchuk.teagile.core.service.repository.UserRepository;
import com.idosinchuk.teagile.core.service.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	private UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	public ResponseEntity<?> getLogin(String email, String password) {

		List<CustomMessage> customMessageList = null;
		Resources<CustomMessage> resource = null;

		try {

			byte[] passwordBytes = Base64.getDecoder().decode(password);
			String decodedPassword = new String(passwordBytes);

			UserEntity entityResponse = userRepository.findByEmailAndPassword(email, decodedPassword);

			if (entityResponse == null) {
				customMessageList = ArrayListCustomMessage
						.setMessage("The requested user does not exists. Please try again.", HttpStatus.NO_CONTENT);
				resource = new Resources<>(customMessageList);
				return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			logger.error("An error occurred! {}", e.getMessage());
			return CustomErrorType.returnResponsEntityError(e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
