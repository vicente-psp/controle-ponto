package com.vicente.controleponto.api.generics;

public interface GenericsOperationsService<E> {
	
	E find(Long id);
	E insert(E entity);
	void update(E entity, Long id);
	void delete(Long id);
	
}