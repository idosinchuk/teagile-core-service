package com.idosinchuk.teagile.core.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idosinchuk.teagile.core.service.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findById(int userId);

	UserEntity findByEmail(String email);

	UserEntity findByEmailAndPassword(String email, String password);

}
