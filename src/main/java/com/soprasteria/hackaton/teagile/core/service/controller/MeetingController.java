package com.soprasteria.hackaton.teagile.core.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.hackaton.teagile.core.service.dto.MeetingRequestDTO;
import com.soprasteria.hackaton.teagile.core.service.service.MeetingService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Timed
@Api(value = "API Rest for Meeting.")
@RequestMapping(value = "/api/v1")
public class MeetingController {

	public static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

	@Autowired
	MeetingService meetingService;

	@GetMapping(path = "/projects/{projectId}/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = "Retrieve list of all meetings by projectId.")
	public ResponseEntity<?> getAllMeetingsByProjectId(
			@PathVariable("projectId") int projectId) {

		logger.info("Fetching all meetings by projectId");
		return meetingService.getAllMeetingsByProjectId(projectId);
	}
	
	@PostMapping(path = "/projects/{projectId}/meetings", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = "Add a meeting.")
	public ResponseEntity<?> addMeetingByProjectId(@PathVariable("projectId") int projectId, @Valid @RequestBody MeetingRequestDTO meetingRequestDTO) {

		logger.info("Process add meeting");
		return meetingService.addMeetingByProjectId(projectId, meetingRequestDTO);
	}

	@PatchMapping(path = "/projects/{projectId}/meetings/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = "Update the meeting.")
	public ResponseEntity<?> updateMeetingByProjectId(@PathVariable("projectId") int projectId, @PathVariable("meetingId") int meetingId,
			@RequestBody MeetingRequestDTO meetingRequestDTO) {

		logger.info("Process patch meeting");
		return meetingService.updateMeetingByProjectId(projectId, meetingId, meetingRequestDTO);
	}

	@DeleteMapping(path = "/projects/{projectId}/meetings/{meetingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = "Delete meeting by Id.")
	public ResponseEntity<?> deleteMeetingByProjectId(@PathVariable("projectId") int projectId, @PathVariable("meetingId") int meetingId) {

		logger.info("Deleting meeting with projectId and meetingId {} ", projectId, meetingId);
		return meetingService.deleteMeetingByProjectId(projectId, meetingId);
	}
}
