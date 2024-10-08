package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoEducationFoundException extends RuntimeException{
	
	private String message;
	public NoEducationFoundException(String message)
	{
		this.message=message;
	}
	@Override
	public String getMessage()
	{
		return this.message;
	}
}
