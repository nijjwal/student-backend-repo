package com.nijjwal.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nijjwal.exception.ResourceNotFound;
import com.nijjwal.model.Student;
import com.nijjwal.repository.StudentRepository;

@Controller
//@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	@Autowired
	public StudentRepository repo;

	Logger logger = LoggerFactory.getLogger(StudentController.class);

//	@GetMapping("/")
//	public List<Student> getAllStudents() {
//		return repo.findAll();
//	}
	
//	
//	@RequestMapping("/")
//	public String getRootPage(Model model) {
//		model.addAttribute("name", "nijjwal");
//		return "";
//	}
	
	@RequestMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("name", "nijjwal");
		return "home";
	}
	
	@RequestMapping("/user")
	public String getUserPage(Model model) {
		model.addAttribute("name", "nijjwal");
		return "user";
	}
	
	@RequestMapping("/admin")
	public String getAdminPage(Model model) {
		model.addAttribute("name", "admin");
		return "admin";
	}

	@GetMapping("/search/{id}")
	public Student getStudentById(@PathVariable(value = "id") int id) {
		logger.error("Demo error happened!");
		logger.trace("Logging at trace level!");
		Student student = repo.findById(id).orElseThrow(() -> new ResourceNotFound("No matching id was found!"));

		return student;
	}

	@GetMapping("/customsearch/{id}")
	public Student getCustomStudentInfo(@PathVariable(value = "id") int id) {
		Student result = repo.getCustomInfo(id);

		if (result == null) {
			throw new RuntimeException("No da fou!");
		}

		return result;
	}

	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student std) {
		Student savedObj = repo.save(std);
		return ResponseEntity.ok(savedObj);
	}

	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value="id") int id, @RequestBody Student student) {

		Student studentToUpdate = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Could not update as student was not found!"));

		if (student.getFirstname() != null) {
			studentToUpdate.setFirstname(student.getFirstname());
		}

		if (student.getLastname() != null) {
			studentToUpdate.setLastname(student.getLastname());
		}
		
		
		Student updatedStudent = repo.save(studentToUpdate);
		return ResponseEntity.ok(updatedStudent);
		
	}
	
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable(value="id") int id){
		Student stdToDelete = repo.findById(id).orElseThrow(()-> new ResourceNotFound("cannot delete..."));
		
		repo.delete(stdToDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
