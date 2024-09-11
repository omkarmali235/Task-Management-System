package com.omkar.TaskManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.TaskManagementSystem.model.Task;
import com.omkar.TaskManagementSystem.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createTask")
	@PreAuthorize("hasRole('ADMIN')")
	public Task createTask(@RequestBody Task task) {
		taskService.createTask(task);
		System.out.println(task);
		return task;
	}
}
