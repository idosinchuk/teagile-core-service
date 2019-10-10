package com.idosinchuk.teagile.core.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idosinchuk.teagile.core.service.common.ArrayListCustomMessage;
import com.idosinchuk.teagile.core.service.common.CustomErrorType;
import com.idosinchuk.teagile.core.service.common.CustomMessage;
import com.idosinchuk.teagile.core.service.dto.TaskRequestDTO;
import com.idosinchuk.teagile.core.service.dto.TaskResponseDTO;
import com.idosinchuk.teagile.core.service.entity.ProjectEntity;
import com.idosinchuk.teagile.core.service.entity.TaskEntity;
import com.idosinchuk.teagile.core.service.repository.ProjectRepository;
import com.idosinchuk.teagile.core.service.repository.TaskRepository;
import com.idosinchuk.teagile.core.service.service.TaskService;

@Service("Taskservice")
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;
	private ProjectRepository projectRepository;
	private ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

	public static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	public ResponseEntity<?> getAllTasksByProjectId(int projectId) {

		Resources<CustomMessage> resource = null;
		List<CustomMessage> customMessageList = null;
		List<TaskResponseDTO> tasks = new ArrayList<>();

		try {
			List<TaskEntity> entityResponse = taskRepository.findByProjectId(projectId);

			if (entityResponse.isEmpty()) {
				customMessageList = ArrayListCustomMessage.setMessage("There are not meetings!", HttpStatus.NO_CONTENT);
				resource = new Resources<>(customMessageList);
				return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
			}

			// Convert Entity response to DTO
			tasks = modelMapper.map(entityResponse, new TypeToken<List<TaskResponseDTO>>() {
			}.getType());

		} catch (Exception e) {
			logger.error("An error occurred! {}", e.getMessage());
			return CustomErrorType.returnResponsEntityError(e.getMessage());
		}

		return new ResponseEntity<>(tasks, HttpStatus.OK);

	}

	@Transactional
	public ResponseEntity<?> addMeetingByProjectId(int projectId, TaskRequestDTO taskRequestDTO) {

		List<CustomMessage> customMessageList = null;
		Resources<CustomMessage> resource = null;
		TaskResponseDTO taskResponseDTO = null;

		try {
			ProjectEntity projectEntity = projectRepository.findById(projectId);

			// Check if projectId exists in the database
			if (projectEntity == null) {
				customMessageList = ArrayListCustomMessage.setMessage(
						"The projectId does not exists. Please try with valid projectId.", HttpStatus.BAD_REQUEST);
				resource = new Resources<>(customMessageList);

				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			checkPriorityAndStatus(taskRequestDTO);

			// Convert taskRequestDTO to TaskEntity
			TaskEntity entityRequest = modelMapper.map(taskRequestDTO, TaskEntity.class);

			TaskEntity taskEntityResponse = taskRepository.save(entityRequest);

			// Convert taskEntityResponse to TaskResponseDTO
			taskResponseDTO = modelMapper.map(taskEntityResponse, TaskResponseDTO.class);

		} catch (Exception e) {
			logger.error("An error occurred! {}", e.getMessage());
			return CustomErrorType.returnResponsEntityError(e.getMessage());
		}

		return new ResponseEntity<>(taskResponseDTO, HttpStatus.OK);

	}

	@Transactional
	public ResponseEntity<?> updateTaskByProjectId(int taskId, int projectId, TaskRequestDTO taskRequestDTO) {

		List<CustomMessage> customMessageList = null;
		Resources<CustomMessage> resource = null;

		try {
			// Check if task exists in the database
			TaskEntity taskEntity = taskRepository.findByIdAndProjectId(taskId, projectId);

			if (taskEntity == null) {
				customMessageList = ArrayListCustomMessage.setMessage("Task Id {}" + taskId + " Not Found!",
						HttpStatus.BAD_REQUEST);
				resource = new Resources<>(customMessageList);
				return new ResponseEntity<>(resource, HttpStatus.BAD_REQUEST);
			}

			TaskEntity entityRequest = modelMapper.map(taskRequestDTO, TaskEntity.class);
			entityRequest.setId(taskId);

			taskRepository.save(entityRequest);

		} catch (Exception e) {
			logger.error("An error occurred! {}", e.getMessage());
			return CustomErrorType.returnResponsEntityError(e.getMessage());

		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	public ResponseEntity<?> deleteTaskByProjectId(int taskId, int projectId) {

		try {
			taskRepository.deleteByIdAndProjectId(taskId, projectId);

		} catch (Exception e) {
			logger.error("An error occurred! {}", e.getMessage());
			return CustomErrorType.returnResponsEntityError(e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// Check if priority and status are present
	private static TaskRequestDTO checkPriorityAndStatus(TaskRequestDTO taskRequestDTO) {

		// If priority is null, set default priority.
		if (taskRequestDTO.getPriority() == 0) {
			taskRequestDTO.setPriority(5);
		}

		// If status is null, set default CREATED status.
		if (taskRequestDTO.getStatus() == null) {
			taskRequestDTO.setStatus("CREATED");
		}

		return taskRequestDTO;
	}
}
