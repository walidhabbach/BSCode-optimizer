package org.gateway.exception.wrapper;


public class ResourceAlreadyExistException extends RuntimeException {
	public ResourceAlreadyExistException(String message) {
		super(message);
	}
}