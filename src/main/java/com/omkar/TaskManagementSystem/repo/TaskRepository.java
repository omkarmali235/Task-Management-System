package com.omkar.TaskManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omkar.TaskManagementSystem.model.Task;
import com.omkar.TaskManagementSystem.model.Users;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//	List<Task> findByAssignedUser(Users user);

}
