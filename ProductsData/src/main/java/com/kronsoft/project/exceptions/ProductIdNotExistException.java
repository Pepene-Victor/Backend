package com.kronsoft.project.exceptions;

public class ProductIdNotExistException extends Exception{


	private static final long serialVersionUID = 1L;

	public ProductIdNotExistException(String id) {
		super("Product with id: " + id + " doesn't exist");
	}
}
