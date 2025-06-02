package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

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
		 return obj.get();
	 }
	 
	 public User insert (User obj) {
		return userRepository.save(obj);
	 }
	 
	 public void delete(Long id) {
		 userRepository.deleteById(id);
	 }
	 
	 public User Update(Long id, User user) {
		 User entity = userRepository.getReferenceById(id);
		 updateData(entity, user);
		 return userRepository.save(entity);
	 }

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
		
	}
}

