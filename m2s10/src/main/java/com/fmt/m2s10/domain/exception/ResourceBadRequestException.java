package com.fmt.m2s10.domain.exception;

public class ResourceBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceBadRequestException(String message) {
		super(message);
	}	
	
}