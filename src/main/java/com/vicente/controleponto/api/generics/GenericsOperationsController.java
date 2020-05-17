package com.vicente.controleponto.api.generics;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;


public interface GenericsOperationsController<E> {
	
	ResponseEntity<E> get(Long id);
	ResponseEntity<E> post(E entity, HttpServletResponse response);
	void put(E entity, Long id);
	void delete(Long id);
	
}