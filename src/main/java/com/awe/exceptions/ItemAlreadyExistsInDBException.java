package com.awe.exceptions;

public class ItemAlreadyExistsInDBException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistsInDBException(String message) {
		super(message);
	}
}
