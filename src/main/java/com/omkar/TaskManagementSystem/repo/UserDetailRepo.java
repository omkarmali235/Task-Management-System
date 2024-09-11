package com.omkar.TaskManagementSystem.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.omkar.TaskManagementSystem.model.Users;

@Repository
public interface UserDetailRepo extends JpaRepository<Users,Integer> {

	Users findByUsername(String username);
/*
 * We have to create **interface** of Model whichever we want to create table and extend
 * that to Jparepository and pass that Class name with primarykey datatype 
 */

}
