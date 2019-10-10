package com.idosinchuk.teagile.core.service.service;

import org.springframework.http.ResponseEntity;

import com.idosinchuk.teagile.core.service.dto.TaskRequestDTO;

public interface TaskService {

	ResponseEntity<?> getAllTasksByProjectId(int projectId);

	ResponseEntity<?> addMeetingByProjectId(int projectId, TaskRequestDTO taskRequestDTO);

	ResponseEntity<?> updateTaskByProjectId(int taskId, int projectId, TaskRequestDTO taskRequestDTO);

	ResponseEntity<?> deleteTaskByProjectId(int taskId, int projectId);

}
