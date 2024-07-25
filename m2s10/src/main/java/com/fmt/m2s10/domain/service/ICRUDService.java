package com.fmt.m2s10.domain.service;

import java.util.List;

public interface ICRUDService<Request, Response> {
	
	List<Response> obterTodos();
	
	Response obterPorId(Long id);
	
	Response cadastrar(Request dto);
	
	Response atualizar(Request dto, Long id);
	
	void deletar(Long id);
	
}