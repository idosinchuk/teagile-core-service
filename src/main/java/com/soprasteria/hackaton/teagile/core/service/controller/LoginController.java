package com.soprasteria.hackaton.teagile.core.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.hackaton.teagile.core.service.service.LoginService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Timed
@Api(value = "API Rest for Login.")
@RequestMapping(value = "/api/v1")
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = " Check login by email and password")
	public ResponseEntity<?> getLogin(@RequestParam("email") String email, @RequestParam("password") String password) {

		logger.info("Fetching user by email and password with email {} ", email);
		return loginService.getLogin(email, password);

	}
}
