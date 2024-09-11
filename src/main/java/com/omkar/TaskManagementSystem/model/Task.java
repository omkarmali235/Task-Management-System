package com.omkar.TaskManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(Long id, String title, String description, String status, String priority, LocalDateTime deadline) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", deadline=" + deadline + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	private String title;
	private String description;
	private String status;
	private String priority;
	private LocalDateTime deadline;
	
//	@ManyToOne
//    @JoinColumn(name = "username", referencedColumnName = "username")
//    private Users assignedUser;
}
