package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exception.DataBaseException;
import com.educandoweb.course.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return 	userRepository.findAll();
	}
	
	 public User findById(Long id) {
		 Optional<User> obj = userRepository.findById(id);
		 //retorna o obj do tipo user que estiver dentro do obj.
		 return obj.orElseThrow(() -> new ResourceNotFoundException(id) );
	 }
	 
	 public User insert (User obj) {
		return userRepository.save(obj);
	 }
	 
	 public void delete(Long id) {
		 try {
			 userRepository.deleteById(id);
		 }
		 catch(EmptyResultDataAccessException e) {
			 throw new ResourceNotFoundException(id);
		 }
		 catch(DataIntegrityViolationException e) {
			 throw new DataBaseException(e.getMessage());
		 }
	 }
	 
	 public User Update(Long id, User user) {
		 try {
			 User entity = userRepository.getReferenceById(id);
			 updateData(entity, user);
			 return userRepository.save(entity);
		 }
		 catch(EntityNotFoundException e) {
			 throw new ResourceNotFoundException(id);
		 }
	 }

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
		
	}
}

