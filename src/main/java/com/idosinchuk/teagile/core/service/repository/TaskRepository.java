package com.idosinchuk.teagile.core.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idosinchuk.teagile.core.service.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

	TaskEntity findByIdAndProjectId(int id, int projectId);

	void deleteByIdAndProjectId(int id, int projectId);

	List<TaskEntity> findByProjectId(int projectId);
}
