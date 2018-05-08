package com.awe.exceptions;

public class NoSuchCategoryInDBException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchCategoryInDBException(String message) {
		super(message);
	}
}