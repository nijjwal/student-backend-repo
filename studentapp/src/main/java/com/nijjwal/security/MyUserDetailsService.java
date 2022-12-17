package com.nijjwal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nijjwal.model.Student;
import com.nijjwal.repository.StudentRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	public StudentRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
		
		Student user  = repo.findByFirstName(firstName);
		if(user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		return new UserPrincipal(user);
	}

}
