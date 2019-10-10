package com.idosinchuk.teagile.core.service.service;

import org.springframework.http.ResponseEntity;

import com.idosinchuk.teagile.core.service.dto.ProjectRequestDTO;

public interface ProjectService {

	ResponseEntity<?> getAllProjectsByUserId(int userId);

	ResponseEntity<?> getProjectByProjectIdAndUserId(int projectId, int userId);

	ResponseEntity<?> addProject(ProjectRequestDTO projectRequestDTO, int userId);

	ResponseEntity<?> addUserToProject(int projectId, int userId);

	ResponseEntity<?> updateProject(int id, ProjectRequestDTO projectRequestDTO);

	ResponseEntity<?> deleteProject(int id);
}
