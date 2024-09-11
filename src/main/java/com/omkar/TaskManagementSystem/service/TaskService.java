package com.omkar.TaskManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.TaskManagementSystem.model.Task;
import com.omkar.TaskManagementSystem.repo.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;

	public String createTask(Task task) {
		taskRepo.save(task);
		return "DONE";
	}
	
	
	

}
