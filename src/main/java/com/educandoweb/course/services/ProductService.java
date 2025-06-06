package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return 	productRepository.findAll();
	}
	 public Product findById(Long id) {
		 Optional<Product> obj = productRepository.findById(id);
		 //retorna o obj do tipo user que estiver dentro do obj.
		 return obj.get();
	 }
}

