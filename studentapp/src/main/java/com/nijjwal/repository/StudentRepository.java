package com.nijjwal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nijjwal.dto.CustomStudentResponse;
import com.nijjwal.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("SELECT new com.nijjwal.dto.CustomStudentResponse(std.firstName)"
			+ " FROM Student std where id = :id")
	public CustomStudentResponse getCustomStudentInfo(int id);
	
	
	@Query("SELECT new com.nijjwal.model.Student(std.id, std.firstName, std.lastName, std.email, '', std.role)"
			+ " FROM Student std where std.id = :id")
	public Student getCustomInfo(int id); 
	
	
	public Student findByFirstName(String firstName);
}
