package com.idosinchuk.teagile.core.service.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idosinchuk.teagile.core.service.dto.TaskRequestDTO;
import com.idosinchuk.teagile.core.service.service.TaskService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Timed
@Api(value = "API Rest for Task.")
@RequestMapping(value = "/api/v1")
public class TaskController {

	public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;

	@GetMapping(path = "/projects/{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ApiOperation(value = "Retrieve list of all tasks.")
	public ResponseEntity<?> getAllTasksByProjectId(@PathVariable("projectId") int projectId) {

		logger.info("Fetching all tasks by projectId");
		return taskService.getAllTasksByProjectId(projectId);
	}

	@PostMapping(path = "/projects/{projectId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a task.")
	public ResponseEntity<?> addTaskByProjectId(@PathVariable("projectId") int projectId, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {

		logger.info("Process add task");
		return taskService.addMeetingByProjectId(projectId, taskRequestDTO);
	}

	@PatchMapping(path = "/projects/{projectId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update the task.")
	public ResponseEntity<?> updateTaskByProjectIdAndMeetingId(@RequestParam("taskId") int taskId,
			@RequestParam("projectId") int projectId, @RequestBody TaskRequestDTO taskRequestDTO) {

		logger.info("Process patch task");
		return taskService.updateTaskByProjectId(taskId, projectId, taskRequestDTO);
	}

	@DeleteMapping(path = "/projects/{projectId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete the task")
	public ResponseEntity<?> deleteTaskByProjectIdAndMeetingId(@RequestParam("taskId") int taskId,
			@RequestParam("projectId") int projectId) {

		logger.info("Deleting task with taskId and projectId{} ", taskId);
		return taskService.deleteTaskByProjectId(taskId, projectId);
	}
}
