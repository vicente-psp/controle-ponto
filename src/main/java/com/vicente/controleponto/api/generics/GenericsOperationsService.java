package com.vicente.controleponto.api.generics;

import javax.servlet.http.HttpServletResponse;


public interface GenericsOperationsService<E> {
	
	E find(Long id);
	E insert(E entity);
	void update(E entity, Long id);
	void delete(Long id);
	
}